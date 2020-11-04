package com.professionalandroid.apps.baemin_clone_android.src.myinfoFragment.modifyMyInfo.models

data class WithdrawalResponse (
    val userIdx: Int?,
    val isSuccess: Boolean,
    val code: Int,
    val message: String
)