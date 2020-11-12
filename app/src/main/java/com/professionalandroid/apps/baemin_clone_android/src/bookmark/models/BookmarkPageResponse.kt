package com.professionalandroid.apps.baemin_clone_android.src.bookmark.models

data class BookmarkPageResponse (
    val result: List<Result>,
    val isSuccess: Boolean,
    val code: Int,
    val message: String
)

data class Result (
    val storeIdx: Int,
    val logo: String,
    val title: String,
    val avgStar: String,
    val reviewNum: String,
    val recommendation: String,
    val deliveryTime: String,
    val minOrderAmount: String,
    val tip: String
)
