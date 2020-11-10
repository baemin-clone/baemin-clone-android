package com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_menu.models

data class ShopDetailMenuResponse (
    val result: Result,
    val isSuccess: Boolean,
    val code: Int,
    val message: String
)

data class Result (
    val description: String,
    val menu: List<Menu>
)

data class Menu (
    val menuCategory: String,
    val highlight: Boolean,
    val contents: List<Content>
)

data class Content (
    val menuIdx: Int,
    val menuTitle: String,
    val menuDescription: String,
    val price: String,
    val photoPath: String
)
