package com.professionalandroid.apps.baemin_clone_android.src.map.models

data class DeleteMapResponse(
    val locationIdx: Int,
    val isSuccess: Boolean,
    val code: Int,
    val message: String
)