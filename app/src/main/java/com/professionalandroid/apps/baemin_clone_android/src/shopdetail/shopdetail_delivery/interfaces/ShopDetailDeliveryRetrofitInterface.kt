package com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_delivery.interfaces

import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_delivery.models.ShopDetailDeliveryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ShopDetailDeliveryRetrofitInterface {
    @GET("/store/{idx}/delivery-info")
    fun getShopDetailDelivery(
        @Path("idx") idx: Int
    ): Call<ShopDetailDeliveryResponse>
}