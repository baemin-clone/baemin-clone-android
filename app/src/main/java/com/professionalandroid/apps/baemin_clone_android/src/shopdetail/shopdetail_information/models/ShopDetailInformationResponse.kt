package com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_information.models

data class ShopDetailInformationResponse (
    val result: Result,
    val isSuccess: Boolean,
    val code: Int,
    val message: String
)

data class Result (
    val description: String,
    val guide: String,
    val statistics: Statistics,
    val info: Info
)

data class Info (
    val operatingTime: String,
    val closedDays: String,
    val phone: String,
    val deliveryZone: String
)

data class Statistics (
    val recentOrderNum: String,
    val reviewNum: String,
    val bookmarkNum: String
)
