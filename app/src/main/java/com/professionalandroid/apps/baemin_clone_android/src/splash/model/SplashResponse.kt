package com.professionalandroid.apps.baemin_clone_android.src.splash.model

data class SplashResponse (
    val info: Info?,
    val isSuccess: Boolean,
    val code: Int,
    val message: String
)

data class Info (
    val address: String?,
    val idx: Int?,
    val iat: Int?,
    val exp: Int?,
    val sub: String?
)
