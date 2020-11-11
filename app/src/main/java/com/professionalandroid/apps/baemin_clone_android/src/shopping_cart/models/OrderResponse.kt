package com.professionalandroid.apps.baemin_clone_android.src.shopping_cart.models

data class OrderResponse (
    val result: Result1,
    val isSuccess: Boolean,
    val code: Int,
    val message: String
)

data class Result1 (
    val orderIdx: Int,
    val status: String
)
