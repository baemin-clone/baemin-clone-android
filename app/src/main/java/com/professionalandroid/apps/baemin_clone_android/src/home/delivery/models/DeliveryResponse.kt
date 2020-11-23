package com.professionalandroid.apps.baemin_clone_android.src.home.delivery.models

data class DeliveryResponse (
    val result: List<Result>,
    val isSuccess: Boolean,
    val code: Int,
    val message: String
)

data class Result (
    val brandIdx: Int,
    val logo: String,
    val title: String,
    val description: String,
    val coupon: Int,
    val newMenu: Int,
    val notice: String
)
