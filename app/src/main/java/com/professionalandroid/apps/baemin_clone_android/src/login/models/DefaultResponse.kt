package com.professionalandroid.apps.baemin_clone_android.src.login.models


data class DefaultResponse(
    var result: Result?,
    var isSuccess: Boolean,
    var code: Int,
    var message: String
)

data class Result(
    var jwt: String?,
    var email: String?
)