package com.professionalandroid.apps.baemin_clone_android.src.shopdetail.models

data class ShopDetailResponse (
    val result: Result?,
    val isSuccess: Boolean,
    val code: Int,
    val message: String
)

data class Result (
    val storeIdx: Int?,
    val title: String?,
    val star: Double?,
    val reviewNum: Int?,
    val phone: String?,
    val bookmarkNum: Int?,
    val isBookmark: Boolean?,
    val orderAvailability: Boolean?
)

