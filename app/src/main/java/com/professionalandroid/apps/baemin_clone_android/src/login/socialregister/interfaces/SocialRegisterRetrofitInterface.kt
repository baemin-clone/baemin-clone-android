package com.professionalandroid.apps.baemin_clone_android.src.login.socialregister.interfaces

import com.professionalandroid.apps.baemin_clone_android.NewSocialUserInfo
import com.professionalandroid.apps.baemin_clone_android.src.login.socialregister.models.SocialResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SocialRegisterRetrofitInterface {
    // Social Login
    @POST("/user-info")
    fun socialLogin(
        @Body data: NewSocialUserInfo
    ): Call<SocialResponse>
}