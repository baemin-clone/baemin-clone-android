package com.professionalandroid.apps.baemin_clone_android

data class email(
    val email: String
)

data class NewUserInfo(
    val email: String,
    val pwd: String,
    val nickname: String,
    val birth: String
)

data class UserIdSet(
    val email: String,
    val pwd: String
)

data class socailLoginToken(
    val accessToken: String
)

data class NewSocialUserInfo(
    val nickname: String,
    val birth: String,
    val accessToken: String
)

data class NowAddress(
    val roadAddress: String?,
    val address: String,
    val longitude: Double,
    val latitude: Double
)

data class ShoppingItem(
    val menuidx: Int,
    val menuNum: Int,
    val optionArray: MutableList<OptionArray>
)

data class OptionArray (
    val optionGroupIdx: Int,
    val options: MutableList<Int>
)

data class ShoppingCart (
    val storeIdx: Int,
    val menuIdx: Int,
    val optionArray: List<OptionArray>
)

data class Order(
    val storeIdx: Int,
    val method: String,
    val menu: List<MenuItem>
)

data class MenuItem(
    val menuIdx: Int,
    val menuNum: Int,
    val optionIdxArray: List<Int>
)

data class InfoChange(
    val nickname: String?,
    val pwd: String?
)