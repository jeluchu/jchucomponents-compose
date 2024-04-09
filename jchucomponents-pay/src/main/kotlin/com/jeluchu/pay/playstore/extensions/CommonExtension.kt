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