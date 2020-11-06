package com.professionalandroid.apps.baemin_clone_android.src.map.navermap.interfaces

import com.professionalandroid.apps.baemin_clone_android.src.map.navermap.models.NaverMapsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NaverMapRetrofitInterface {
    @GET("/map-reversegeocode/v2/gc")
    fun reverse_geocoding(
        @Header("X-NCP-APIGW-API-KEY-ID") header1: String,
        @Header("X-NCP-APIGW-API-KEY") header2: String,
        @Query("coords") coords: String,
        @Query("output") output: String,
        @Query("orders") orders: String
    ): Call<NaverMapsResponse>
}