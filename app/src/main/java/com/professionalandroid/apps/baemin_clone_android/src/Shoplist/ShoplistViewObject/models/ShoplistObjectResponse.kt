package com.professionalandroid.apps.baemin_clone_android.src.Shoplist.ShoplistViewObject.models

data class ShoplistObjectResponse (
    val result: List<Result>,
    val isSuccess: Boolean,
    val code: Int,
    val message: String
)

data class Result (
    val storeIdx: Int,
    val logo: String,
    val title: String,
    val star: String,
    val reviewNum: String,
    val recommendation: String,
    val deliveryTIME: String,
    val minOrderAmount: String,
    val tip: String
)
