package com.professionalandroid.apps.baemin_clone_android.src.login.socialregister.interfaces

import com.professionalandroid.apps.baemin_clone_android.socailLoginToken
import com.professionalandroid.apps.baemin_clone_android.src.login.models.DefaultResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SocialRegisterRetrofitInterface {
    // Social Login
    @POST("/naver-login")
    fun socialLogin(
        @Body data: socailLoginToken
    ): Call<DefaultResponse>
}