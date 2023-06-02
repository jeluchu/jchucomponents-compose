package com.jeluchu.pay.playstore.enums

enum class BillingState {
    BILLING_CONNECTED,
    BILLING_CONNECTION_FAILED,
    BILLING_DISCONNECTED,
    BILLING_LOST_CONNECTION,
    PREVIOUS_PURCHASE_FOUND,
    NO_MATCH_PRODUCTS_FOUND,
    PURCHASE_COMPLETE,
    PURCHASE_FAILED,
    PURCHASE_CANCELLED,
}