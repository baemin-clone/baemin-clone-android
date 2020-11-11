package com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_menu.shop_item.models

data class ShopDetailItemResponse (
    val result: Result?,
    val isSuccess: Boolean,
    val code: Int,
    val message: String
)

data class Result (
    val photoPath: String?,
    val menuTitle: String?,
    val details: String?,
    val basicPrice: Int?,
    val options: List<Option>
)

data class Option (
    val optionGroupIdx: Int?,
    val groupTitle: String?,
    val required: Boolean?,
    val contents: List<Content>
)

data class Content (
    val optionIdx: Int?,
    val title: String?,
    val price: Int?
)
