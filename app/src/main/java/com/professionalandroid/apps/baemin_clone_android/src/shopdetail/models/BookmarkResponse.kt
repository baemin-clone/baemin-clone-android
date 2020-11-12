package com.professionalandroid.apps.baemin_clone_android.src.shopdetail.models

data class BookmarkResponse (
    val result: Result1,
    val isSuccess: Boolean,
    val code: Int,
    val message: String
)

data class Result1 (
    val storeIdx: Int,
    val bookmarkStatus: String
)
