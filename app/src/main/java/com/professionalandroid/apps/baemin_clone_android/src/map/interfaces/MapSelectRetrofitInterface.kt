package com.professionalandroid.apps.baemin_clone_android.src.map.interfaces

import com.professionalandroid.apps.baemin_clone_android.src.map.models.MapResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MapSelectRetrofitInterface {
    @GET("/my-address")
    fun getMapList(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Call<MapResponse>
}