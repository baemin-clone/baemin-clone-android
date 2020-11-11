package com.professionalandroid.apps.baemin_clone_android.src.homeFragment.delivery.interfaces

import com.professionalandroid.apps.baemin_clone_android.src.homeFragment.delivery.models.DeliveryResponse
import com.professionalandroid.apps.baemin_clone_android.src.homeFragment.delivery.models.RecommendationResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DeliveryInterface {
    @GET("/brand")
    fun brand(

    ): Call<DeliveryResponse>

    @GET("/recommend")
    fun recommendation(
      @Query("tag") tag: String
    ): Call<RecommendationResponse>

}


