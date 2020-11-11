package com.professionalandroid.apps.baemin_clone_android.src.shopping_cart.models

data class ShoppingCartItemResponse (
    val result: Result,
    val isSuccess: Boolean,
    val code: Int,
    val message: String
)

data class Result (
    val menuTitle: String,
    val options: List<String>,
    val price: Int
)

data class ShoppingCartItem(
    val shopping: ShoppingCartItemResponse,
    val num: Int
)