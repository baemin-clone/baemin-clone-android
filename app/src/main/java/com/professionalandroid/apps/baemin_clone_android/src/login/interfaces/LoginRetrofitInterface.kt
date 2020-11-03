package com.professionalandroid.apps.baemin_clone_android.src.login.interfaces

import com.professionalandroid.apps.baemin_clone_android.NewUserInfo
import com.professionalandroid.apps.baemin_clone_android.UserIdSet
import com.professionalandroid.apps.baemin_clone_android.socailLoginToken
import com.professionalandroid.apps.baemin_clone_android.src.login.models.DefaultResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginRetrofitInterface {
    // Register
    @POST("/signup")
    fun registerNewId(
        @Body data: NewUserInfo
    ): Call<DefaultResponse>

    // General Login
    @POST("/login")
    fun login(
        @Body data: UserIdSet
    ): Call<DefaultResponse>

    // Social Login
    @POST("/naver-login")
    fun socialLogin(
        @Body data: socailLoginToken
    ): Call<DefaultResponse>

}