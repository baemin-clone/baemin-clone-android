package com.professionalandroid.apps.baemin_clone_android.src.shoplist.shoplistViewObject.interfaces

import com.professionalandroid.apps.baemin_clone_android.src.shoplist.shoplistViewObject.models.ShoplistObjectResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ShoplistViewObjectRetrofitInterface {
    @GET("/store")
    fun getShoplist(
        @Query("category") category: String,
        @Query("order") order: String?,
        @Query("minAmount") minAmount: Int?,
        @Query("tip") tip: Int?,
        @Query("star") star: Double?,
        @Query("page") page: Int?,
        @Query("size") size:Int?
    ): Call<ShoplistObjectResponse>
}