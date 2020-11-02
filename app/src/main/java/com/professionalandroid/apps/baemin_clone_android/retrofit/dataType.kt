package com.professionalandroid.apps.baemin_clone_android.retrofit

data class email(
    var email: String
)

data class email_response(
    var isExist: Boolean?,
    var isSuccess: Boolean,
    var code: Int,
    var message: String
)

data class NewUserInfo(
    var email: String,
    var pwd: String,
    var nickname: String,
    var birth: String
)

data class NewUserInfo_response(
    var result: Any?,
    var email: String?,
    var jwt: String?,
    var isSuccess: Boolean,
    var code: Int,
    var message: String
)