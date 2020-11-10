package com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_information.interfaces

import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_information.models.ShopDetailInformationResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ShopDetailInformationRetrofitInterface {
    @GET("/store/{store-idx}/info-details")
    fun shopDetailInformation(
        @Path("store-idx") idx: Int
    ):Call<ShopDetailInformationResponse>
}