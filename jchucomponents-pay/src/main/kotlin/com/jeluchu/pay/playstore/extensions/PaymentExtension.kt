package com.jeluchu.pay.playstore.extensions

import android.content.Context
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.ProductDetails
import com.android.billingclient.api.Purchase
import com.jeluchu.pay.playstore.Payment
import com.revenuecat.purchases.LogLevel
import com.revenuecat.purchases.Purchases
import com.revenuecat.purchases.PurchasesConfiguration

/**
 *
 * Author: @Jeluchu
 *
 * Init RevenueCat Configuration
 * @see <a href="https://www.revenuecat.com/docs/welcome/overview">RevenueCat Documentation</a>
 * @see <a href="https://www.revenuecat.com/docs/welcome/authentication">RevenueCat API Key</a>
 *
 * @param apiKey [String] In this value you must pass the key generated by RevenueCat
 * to obtain all the information of the products offered to the users
 * @param isDebug [Boolean] This parameter is used to indicate if the build variant
 * you are using is Debug or not, to show you more or less errors depending on the environment
 * @param subscriptionName [String] This will be the name you have indicated in RevenueCat
 * for your subscription, inside then you can have different types (Monthly, Annual, etc)
 *
 */
fun Context.initPayment(
    apiKey: String,
    isDebug: Boolean,
    subscriptionName: String
) {
    if (isDebug) Purchases.logLevel = LogLevel.DEBUG
    Purchases.configure(PurchasesConfiguration.Builder(this, apiKey).build())
    Payment.setSubscriptionName(subscriptionName)
}

/**
 * Determine if purchaseState is purchased ([Purchase.PurchaseState.PURCHASED])
 */
fun Purchase.isPurchased(): Boolean =
    purchaseState == Purchase.PurchaseState.PURCHASED

/**
 * Filters out in app purchases ([BillingClient] with type [BillingClient.ProductType.INAPP])
 */
fun List<ProductDetails>.getInAppPurchases(): List<ProductDetails> =
    this.filter { it.productType == BillingClient.ProductType.INAPP }

/**
 * Filters out subscriptions ([BillingClient] with type [BillingClient.ProductType.SUBS])
 */
fun List<ProductDetails>.getSubscriptions(): List<ProductDetails> =
    this.filter { it.productType == BillingClient.ProductType.SUBS }

/**
 * Determine if type is in app purchase ([BillingClient.ProductType.INAPP])
 */
fun ProductDetails.isInAppPurchase(): Boolean =
    productType == BillingClient.ProductType.INAPP

/**
 * Determine if type is a subscription ([BillingClient.SkuType.SUBS])
 */
fun ProductDetails.isSubscription(): Boolean =
    productType == BillingClient.ProductType.SUBS