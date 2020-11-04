package com.professionalandroid.apps.baemin_clone_android.src.login.models

data class DefaultResponse(
    val result: Result?,
    val isSuccess: Boolean,
    val code: Int,
    val message: String
)

data class Result(
    val email: String?,
    val jwt: String?
)