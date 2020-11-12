package com.professionalandroid.apps.baemin_clone_android.src.history.models

data class HistoryResponse (
    val result: List<Result>,
    val isSuccess: Boolean,
    val code: Int,
    val message: String
)

data class Result (
    val storeIdx: Int,
    val logo: String,
    val title: String,
    val orderHistory: String,
    val hashtag: String,
    val createdAt: String,
    val isWrite: Boolean
)
