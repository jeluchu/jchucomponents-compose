package com.jeluchu.pay.revenuecat.models

import com.revenuecat.purchases.Package

data class BillingInfo(
    val info: SubscriptionInfo,
    val packages: List<Package>,
    val products: List<Product>
) {
    companion object {
        fun empty() = BillingInfo(
            packages = emptyList(),
            products = emptyList(),
            info = SubscriptionInfo.empty()
        )
    }
}