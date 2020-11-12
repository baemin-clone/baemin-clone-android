package com.professionalandroid.apps.baemin_clone_android.src.history.interfaces

import com.professionalandroid.apps.baemin_clone_android.src.history.models.HistoryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface HistoryRetrofitInterface {
    @GET("/bookmark")
    fun getHistory(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Call<HistoryResponse>
}