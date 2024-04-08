package com.jeluchu.pay.playstore.extensions

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.ProductDetails
import com.android.billingclient.api.Purchase
import java.math.RoundingMode

internal fun String.Companion.empty() = ""
internal fun Float.roundTo(n: Int): Float = toBigDecimal().setScale(n, RoundingMode.UP).toFloat()
internal fun Double.roundTo(n: Int): Double = toBigDecimal().setScale(n, RoundingMode.UP).toDouble()

internal fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
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