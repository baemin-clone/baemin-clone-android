package com.professionalandroid.apps.baemin_clone_android.src.map.models

data class MapResponse (
    val result: Result,
    val isSuccess: Boolean,
    val code: Int,
    val message: String
)

data class Result (
    val userLocationRows: List<UserLocation>,
    val page: Int,
    val size: Int
)

data class UserLocation (
    val idx: Int,
    val roadAddress: String,
    val address: String
)