package com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_visit.models

data class ShopDetailVisitResponse (
    val result: Result?,
    val isSuccess: Boolean,
    val code: Int,
    val message: String
)

data class Result (
    val minOrderAmount: String?,
    val use: String?,
    val cookingTime: String?,
    val address: String?,
    val longitude: Double?,
    val latitude: Double?,
    val payment: String?
)
