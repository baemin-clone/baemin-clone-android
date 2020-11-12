package com.professionalandroid.apps.baemin_clone_android.src.bookmark.interfaces

import com.professionalandroid.apps.baemin_clone_android.src.bookmark.models.BookmarkPageResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FirstBookmarkRetrofitInterface {
    @GET("/bookmark")
    fun getBookmark(
        @Query("page") page: Int,
        @Query("size") size: Int
    ):Call<BookmarkPageResponse>
}