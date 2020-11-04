package com.professionalandroid.apps.baemin_clone_android.src.login.register.models

data class EmailResponse(
    val isExist: Boolean?,
    val isSuccess: Boolean,
    val code: Int,
    val message: String
)