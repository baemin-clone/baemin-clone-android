package com.professionalandroid.apps.baemin_clone_android.src.map.navermap.additional.models

data class NowAddressResponse (
    val currentAddress: String,
    val isSuccess: Boolean,
    val code: Int,
    val message: String
)
