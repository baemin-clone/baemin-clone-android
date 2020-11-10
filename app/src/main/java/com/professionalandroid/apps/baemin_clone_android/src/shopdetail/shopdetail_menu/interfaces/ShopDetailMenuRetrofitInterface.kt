package com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_menu.interfaces

import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_menu.models.ShopDetailMenuResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ShopDetailMenuRetrofitInterface {
    @GET("/store/{store-index}/menu-list")
    fun shopDetailMenu(
        @Path("store-index") idx: Int
    ):Call<ShopDetailMenuResponse>
}