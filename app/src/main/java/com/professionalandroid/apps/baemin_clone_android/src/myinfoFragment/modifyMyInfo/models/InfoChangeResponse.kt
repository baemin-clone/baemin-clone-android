package com.professionalandroid.apps.baemin_clone_android.src.myinfoFragment.modifyMyInfo.models

data class InfoChangeResponse (
    val result: Result,
    val isSuccess: Boolean,
    val code: Int,
    val message: String
)

data class Result1 (
    val nickname: String,
    val email: String,
    val phone: String
)
