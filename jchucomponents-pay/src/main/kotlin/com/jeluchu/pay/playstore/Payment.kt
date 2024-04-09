package com.jeluchu.pay.playstore

import android.content.Context
import com.jeluchu.pay.playstore.extensions.empty
import com.jeluchu.pay.playstore.extensions.findActivity
import com.jeluchu.pay.playstore.extensions.roundTo
import com.jeluchu.pay.playstore.models.BillingInfo
import com.jeluchu.pay.playstore.models.Product
import com.jeluchu.pay.playstore.models.SubscriptionInfo
import com.jeluchu.pay.playstore.models.SubscriptionState
import com.jeluchu.pay.playstore.models.SubscriptionsType
import com.revenuecat.purchases.CustomerInfo
import com.revenuecat.purchases.LogLevel
import com.revenuecat.purchases.Package
import com.revenuecat.purchases.PackageType
import com.revenuecat.purchases.PurchaseParams
import com.revenuecat.purchases.Purchases
import com.revenuecat.purchases.PurchasesConfiguration
import com.revenuecat.purchases.PurchasesError
import com.revenuecat.purchases.PurchasesErrorCode
import com.revenuecat.purchases.getOfferingsWith
import com.revenuecat.purchases.interfaces.PurchaseCallback
import com.revenuecat.purchases.interfaces.ReceiveCustomerInfoCallback
import com.revenuecat.purchases.models.GoogleProrationMode
import com.revenuecat.purchases.models.StoreTransaction
import com.revenuecat.purchases.models.googleProduct
import com.revenuecat.purchases.purchaseWith
import com.revenuecat.purchases.restorePurchasesWith
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.math.roundToLong

class Payment {
    var subscriptionName: String = String.empty()

    private fun getProducts(
        onSuccess: (BillingInfo) -> Unit,
        onFailure: (PurchasesError) -> Unit = {}
    ) = Purchases.sharedInstance.getOfferingsWith({ error -> onFailure(error) }) { offerings ->
        getCustomerInfo { customerInfo ->
            customerInfo?.let { info ->
                offerings.current?.availablePackages?.takeUnless { it.isEmpty() }?.let { packages ->
                    onSuccess(
                        BillingInfo(
                            info = buildSubscriptionInfo(
                                entitlement = subscriptionName,
                                customerInfo = info,
                                products = packages
                            ),
                            packages = packages,
                            products = buildSubscriptionProducts(packages)
                        )
                    )
                }
            }
        }
    }

    private fun buildSubscriptionProducts(products: List<Package>): List<Product> {
        val monthly = products.find { it.identifier == ProductsType.MONTHLY.type }
        val annual = products.find { it.identifier == ProductsType.ANNUAL.type }

        if (monthly != null && annual != null) {
            val annualPricePerMonth = (annual.product.price.amountMicros.toDouble() / 1000000) / 12
            val pricePerMonth = monthly.product.price.amountMicros.toDouble() / 1000000
            val savings = 100 - ((annualPricePerMonth / pricePerMonth) * 100)

            return listOf(
                Product(
                    isMonthly = true,
                    price = monthly.product.price.formatted,
                    priceConversion = null,
                    saveAmount = null,
                    storeProduct = monthly.product
                ),
                Product(
                    isMonthly = false,
                    price = annual.product.price.formatted,
                    priceConversion = ((annual.product.price.amountMicros.toDouble() / 1000000) / 12)
                        .roundTo(2)
                        .toString(),
                    saveAmount = savings.roundToLong().toString(),
                    storeProduct = annual.product
                )
            )
        }

        return emptyList()
    }

    private fun buildSubscriptionInfo(
        entitlement: String,
        customerInfo: CustomerInfo,
        products: List<Package>
    ): SubscriptionInfo {
        val product =
            products.find { it.product.googleProduct?.productId == customerInfo.entitlements[entitlement]?.productIdentifier }
        var expire = "?"
        var isPromotional = false

        runCatching {
            expire = if (isSubscriptionExpired(customerInfo)) String.empty()
            else SimpleDateFormat(
                "dd/MM/yyyy HH:mm aaa",
                Locale.ROOT
            ).format(customerInfo.entitlements[entitlement]?.expirationDate ?: Date())
        }

        return SubscriptionInfo(
            renewalType = when {
                product?.identifier == ProductsType.MONTHLY.type -> SubscriptionsType.MONTHLY
                product?.identifier == ProductsType.ANNUAL.type -> SubscriptionsType.YEARLY
                PaymentProducts.promos.find { prom -> customerInfo.activeSubscriptions.find { it == prom } != null } != null -> {
                    isPromotional = true
                    SubscriptionsType.PROMO
                }

                else -> SubscriptionsType.NONE
            },
            promotional = isPromotional,
            expireDate = expire,
            state = if (isSubscriptionPaused(
                    entitlement,
                    customerInfo
                )
            ) SubscriptionState.INACTIVE_UNTIL_RENEWAL
            else SubscriptionState.ACTIVE,
            managementUrl = customerInfo.managementURL
        )
    }

    private fun activateSubscription(
        customerInfo: CustomerInfo,
        onActiveSubscription: (Boolean, String) -> Unit
    ) = customerInfo.entitlements[subscriptionName]?.let { entitlementInfo ->
        if (entitlementInfo.isActive) onActiveSubscription(
            true, entitlementInfo.productIdentifier
        )
    }

    private fun isSubscriptionExpired(customerInfo: CustomerInfo): Boolean {
        val calendar = Calendar.getInstance()
        val currentTime = calendar.time
        calendar.time = customerInfo.requestDate

        val date: Date =
            if (currentTime.before(calendar.time)) customerInfo.requestDate else currentTime

        return customerInfo.entitlements[subscriptionName]?.expirationDate != null
                && customerInfo.entitlements[subscriptionName]?.expirationDate?.after(date) == false
    }

    private fun subscriptionActive(customerInfo: CustomerInfo): Boolean {
        return customerInfo.entitlements[subscriptionName]?.isActive == true && !isSubscriptionExpired(
            customerInfo
        )
    }

    private fun isSubscriptionPaused(entitlement: String, customerInfo: CustomerInfo) =
        !subscriptionActive(customerInfo) && customerInfo.entitlements[entitlement]?.willRenew == true

    private fun getCustomerInfo(callback: (CustomerInfo?) -> Unit) {
        Purchases.sharedInstance.getCustomerInfo(object : ReceiveCustomerInfoCallback {
            override fun onError(error: PurchasesError) = callback.invoke(null)
            override fun onReceived(customerInfo: CustomerInfo) = callback.invoke(customerInfo)
        })
    }

    enum class ProductsType(val type: String, val packageType: PackageType) {
        ANNUAL("\$rc_annual", PackageType.ANNUAL),
        MONTHLY("\$rc_monthly", PackageType.MONTHLY),
    }

    companion object {
        private val payment = Payment()
        val userId: String = Purchases.sharedInstance.appUserID

        private val _billingError = MutableStateFlow(PurchasesError(PurchasesErrorCode.UnknownError))
        val billingError: StateFlow<PurchasesError> = _billingError.asStateFlow()

        private val _billingInfo = MutableStateFlow(BillingInfo.empty())
        val billingInfo: StateFlow<BillingInfo> = _billingInfo.asStateFlow()

        fun setSubscriptionName(name: String) {
            payment.subscriptionName = name
        }

        fun getProducts() = payment.getProducts(
            onSuccess = { _billingInfo.value = it },
            onFailure = { _billingError.value = it }
        )

        fun purchase(
            context: Context,
            type: ProductsType,
            onSuccess: (Boolean, String) -> Unit = { _, _ -> },
            onFailure: (PurchasesError, Boolean) -> Unit = { _, _ -> }
        ) = context.findActivity()?.let { activity ->
            billingInfo.value.packages.apply {
                if (isNotEmpty()) {
                    Purchases.sharedInstance.purchaseWith(
                        PurchaseParams.Builder(activity, first { it.packageType == type.packageType }).build(),
                        onError = { error, userCancelled -> onFailure(error, userCancelled) },
                        onSuccess = { _, customerInfo -> payment.activateSubscription(customerInfo, onSuccess) }
                    )
                }
            }
        }

        fun upgradeDownGrade(
            context: Context,
            onSuccess: (Boolean, String) -> Unit = { _, _ -> },
            onFail: (PurchasesError, Boolean) -> Unit = { _, _ -> }
        ) = payment.getCustomerInfo { info ->
            if (info != null) {
                if (billingInfo.value.packages.isNotEmpty()) {
                    val monthlyProduct =
                        billingInfo.value.packages.find { it.packageType == PackageType.MONTHLY }
                    val annualProduct =
                        billingInfo.value.packages.find { it.packageType == PackageType.ANNUAL }

                    info.entitlements[payment.subscriptionName]?.let { entitlementInfo ->
                        when {
                            entitlementInfo.isActive && entitlementInfo.productIdentifier == monthlyProduct?.product?.googleProduct?.productId -> {
                                context.findActivity()?.let { activity ->
                                    annualProduct?.let { product ->
                                        Purchases.sharedInstance.purchase(
                                            PurchaseParams.Builder(activity, product)
                                                .oldProductId(monthlyProduct.product.googleProduct?.productId.orEmpty())
                                                .googleProrationMode(GoogleProrationMode.IMMEDIATE_WITHOUT_PRORATION)
                                                .build(),
                                            object : PurchaseCallback {
                                                override fun onCompleted(
                                                    storeTransaction: StoreTransaction,
                                                    customerInfo: CustomerInfo
                                                ) { payment.activateSubscription(customerInfo, onSuccess) }

                                                override fun onError(
                                                    error: PurchasesError,
                                                    userCancelled: Boolean
                                                ) = onFail(error, userCancelled)
                                            }
                                        )
                                    }
                                }
                            }

                            entitlementInfo.isActive && entitlementInfo.productIdentifier == annualProduct?.product?.googleProduct?.productId -> {
                                context.findActivity()?.let { activity ->
                                    monthlyProduct?.let { product ->
                                        Purchases.sharedInstance.purchase(
                                            PurchaseParams.Builder(activity, product)
                                                .oldProductId(annualProduct.product.googleProduct?.productId.orEmpty())
                                                .googleProrationMode(GoogleProrationMode.IMMEDIATE_WITHOUT_PRORATION)
                                                .build(),
                                            object : PurchaseCallback {
                                                override fun onCompleted(
                                                    storeTransaction: StoreTransaction,
                                                    customerInfo: CustomerInfo
                                                ) { payment.activateSubscription(customerInfo, onSuccess) }

                                                override fun onError(
                                                    error: PurchasesError,
                                                    userCancelled: Boolean
                                                ) = onFail(error, userCancelled)
                                            }
                                        )
                                    }
                                }
                            }

                            else -> {}
                        }
                    }
                } else onFail(PurchasesError(PurchasesErrorCode.NetworkError), false)
            } else onFail(PurchasesError(PurchasesErrorCode.NetworkError), false)
        }

        fun restorePurchases(
            onRestored: (Boolean, String) -> Unit = { _, _ -> },
        ) = Purchases.sharedInstance.restorePurchasesWith { customerInfo ->
            customerInfo.let { info ->
                info.entitlements[payment.subscriptionName]?.let { entitlementInfo ->
                    if (entitlementInfo.isActive) onRestored(true, entitlementInfo.productIdentifier)
                    else onRestored(false, String.empty())
                }
            }
        }

        fun isSubscriptionActive(
            onChecked: (Boolean, String) -> Unit = { _, _ -> },
        ) = payment.getCustomerInfo { customerInfo ->
            customerInfo?.let { info ->
                info.entitlements[payment.subscriptionName]?.let { entitlementInfo ->
                    if (entitlementInfo.isActive && payment.subscriptionActive(customerInfo))
                        onChecked(true, entitlementInfo.productIdentifier)
                    else onChecked(false, String.empty())
                }
            }
        }
    }

    object PaymentProducts {
        val promos = listOf(
            "rc_promo_pro_daily",
            "rc_promo_pro_three_day",
            "rc_promo_pro_weekly",
            "rc_promo_pro_monthly",
            "rc_promo_pro_three_month",
            "rc_promo_pro_six_month",
            "rc_promo_pro_yearly",
            "rc_promo_pro_lifetime",
        )
    }
}