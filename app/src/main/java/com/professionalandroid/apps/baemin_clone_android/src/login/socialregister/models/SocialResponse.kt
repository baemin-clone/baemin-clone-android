package com.professionalandroid.apps.baemin_clone_android.src.login.socialregister.models

data class SocialResponse (
    val result: Result?,
    val isSuccess: Boolean,
    val code: Long,
    val message: String
)

data class Result (
    val jwt: String?,
    val nickname: String?,
    val email: String?,
    val birth: String?
)
