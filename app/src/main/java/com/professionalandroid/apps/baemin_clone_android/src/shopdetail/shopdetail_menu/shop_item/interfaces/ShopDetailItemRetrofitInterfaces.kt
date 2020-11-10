package com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_menu.shop_item.interfaces

import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_menu.shop_item.models.ShopDetailItemResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ShopDetailItemRetrofitInterfaces {
    @GET("/menu-list/{menu-idx}/options")
    fun shopDetailItem(
        @Path("menu-idx") idx: Int
    ):Call<ShopDetailItemResponse>
}