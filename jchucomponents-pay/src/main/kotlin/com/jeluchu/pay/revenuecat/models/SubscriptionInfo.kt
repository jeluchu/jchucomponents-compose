package com.jeluchu.pay.revenuecat.models

import android.net.Uri
import com.jeluchu.pay.revenuecat.extensions.empty

data class SubscriptionInfo(
    val renewalType: SubscriptionsType,
    val expireDate: String,
    val promotional: Boolean,
    val state: SubscriptionState,
    val managementUrl: Uri?
) {
    companion object {
        fun empty() = SubscriptionInfo(
            renewalType = SubscriptionsType.NONE,
            expireDate = String.empty(),
            promotional = false,
            state = SubscriptionState.NONE,
            managementUrl = null
        )
    }
}