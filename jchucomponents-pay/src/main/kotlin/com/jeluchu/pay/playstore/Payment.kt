package com.jeluchu.pay.playstore

import android.content.Context
import com.jeluchu.pay.playstore.extensions.empty
import com.jeluchu.pay.playstore.extensions.roundTo
import com.jeluchu.pay.playstore.models.BillingInfo
import com.jeluchu.pay.playstore.models.Product
import com.jeluchu.pay.playstore.models.SubscriptionInfo
import com.jeluchu.pay.playstore.models.SubscriptionState
import com.jeluchu.pay.playstore.models.SubscriptionsType
import com.revenuecat.purchases.CustomerInfo
import com.revenuecat.purchases.Package
import com.revenuecat.purchases.Purchases
import com.revenuecat.purchases.PurchasesConfiguration
import com.revenuecat.purchases.PurchasesError
import com.revenuecat.purchases.getOfferingsWith
import com.revenuecat.purchases.interfaces.ReceiveCustomerInfoCallback
import com.revenuecat.purchases.models.googleProduct
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.math.roundToLong

object Payment {
    lateinit var subscriptionName: String
    lateinit var annualIdentifier: String
    lateinit var monthlyIdentifier: String

    private val _billingInfo = MutableStateFlow(BillingInfo.empty())
    val billingInfo: StateFlow<BillingInfo> = _billingInfo.asStateFlow()

    fun init(context: Context, apiKey: String) = Purchases.configure(
        PurchasesConfiguration.Builder(context, apiKey).build()
    )

    fun getProducts(
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

    fun buildSubscriptionProducts(products: List<Package>): List<Product> {
        val monthly = products.find { it.identifier == monthlyIdentifier }
        val annual = products.find { it.identifier == annualIdentifier }

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

    fun buildSubscriptionInfo(
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
                product?.identifier == monthlyIdentifier -> SubscriptionsType.MONTHLY
                product?.identifier == annualIdentifier -> SubscriptionsType.YEARLY
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

    fun activateSubscription(
        customerInfo: CustomerInfo,
        onActiveSubscription: (Boolean, String) -> Unit
    ) = customerInfo.entitlements[subscriptionName]?.let { entitlementInfo ->
        if (entitlementInfo.isActive) onActiveSubscription(
            true, entitlementInfo.productIdentifier
        )
    }

    fun isSubscriptionExpired(customerInfo: CustomerInfo): Boolean {
        val calendar = Calendar.getInstance()
        val currentTime = calendar.time
        calendar.time = customerInfo.requestDate

        val date: Date =
            if (currentTime.before(calendar.time)) customerInfo.requestDate else currentTime

        return customerInfo.entitlements[subscriptionName]?.expirationDate != null
                && customerInfo.entitlements[subscriptionName]?.expirationDate?.after(date) == false
    }

    fun subscriptionActive(customerInfo: CustomerInfo): Boolean {
        return customerInfo.entitlements[subscriptionName]?.isActive == true && !isSubscriptionExpired(
            customerInfo
        )
    }

    fun isSubscriptionPaused(entitlement: String, customerInfo: CustomerInfo) =
        !subscriptionActive(customerInfo) && customerInfo.entitlements[entitlement]?.willRenew == true

    fun getCustomerInfo(callback: (CustomerInfo?) -> Unit) {
        Purchases.sharedInstance.getCustomerInfo(object : ReceiveCustomerInfoCallback {
            override fun onError(error: PurchasesError) = callback.invoke(null)
            override fun onReceived(customerInfo: CustomerInfo) = callback.invoke(customerInfo)
        })
    }

    object PaymentProducts {
        const val monthly = "\$rc_monthly"
        const val annual = "\$rc_annual"
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