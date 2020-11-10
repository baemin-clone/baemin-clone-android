package com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_visit.interfaces

import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_visit.models.ShopDetailVisitResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ShopDetailVisitRetrofitInterface {
    @GET("/store/{idx}/take-out")
    fun shopDetailVisit(
        @Path("idx") idx: Int
    ):Call<ShopDetailVisitResponse>
}