package com.professionalandroid.apps.baemin_clone_android.src.homeFragment.delivery.models

data class RecommendationResponse (
    val result: List<Result1>,
    val isSuccess: Boolean,
    val code: Int,
    val message: String
)

data class Result1 (
    val logo: String,
    val title: String,
    val deliveryTime: String,
    val storeIdx: Int,
    val mainMenu: String
)