package com.professionalandroid.apps.baemin_clone_android.src.splash.interfaces

import com.professionalandroid.apps.baemin_clone_android.src.splash.model.SplashResponse
import retrofit2.Call
import retrofit2.http.GET

interface SplashRetrofitInterface {
    @GET("/check")
    fun autoLogin(

    ):Call<SplashResponse>
}