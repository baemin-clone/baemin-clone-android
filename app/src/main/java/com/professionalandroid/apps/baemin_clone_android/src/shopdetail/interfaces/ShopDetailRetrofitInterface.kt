package com.professionalandroid.apps.baemin_clone_android.src.shopdetail.interfaces

import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.models.BookmarkResponse
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.models.ShopDetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface ShopDetailRetrofitInterface {
    @GET("/store/{idx}/info")
    fun getShopDetail(
        @Path("idx") idx: Int
    ): Call<ShopDetailResponse>

    @PATCH("/bookmark/{store-idx}")
    fun modifyBookmark(
        @Path("store-idx") idx:Int
    ):Call<BookmarkResponse>
}

