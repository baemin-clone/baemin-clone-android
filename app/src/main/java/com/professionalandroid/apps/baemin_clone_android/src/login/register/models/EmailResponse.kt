package com.professionalandroid.apps.baemin_clone_android.src.login.register.models

data class EmailResponse(
    var isExist: Boolean?,
    var isSuccess: Boolean,
    var code: Int,
    var message: String
)