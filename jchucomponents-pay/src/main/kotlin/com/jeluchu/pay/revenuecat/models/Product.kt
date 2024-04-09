package com.jeluchu.pay.revenuecat.models

import com.revenuecat.purchases.models.StoreProduct

data class Product(
    val isMonthly: Boolean,
    val price: String,
    val priceConversion: String?,
    val saveAmount: String?,
    val storeProduct: StoreProduct
)