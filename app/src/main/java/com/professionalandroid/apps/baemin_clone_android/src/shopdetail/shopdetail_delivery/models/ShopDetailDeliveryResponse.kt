package com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_delivery.models

data class ShopDetailDeliveryResponse (
    val result: Result?,
    val isSuccess: Boolean,
    val code: Int,
    val message: String
)

data class Result (
    val minOrderAmount: String?,
    val payment: String?,
    val deliveryTime: String?,
    val deliveryTip: String?
)
