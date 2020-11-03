package com.professionalandroid.apps.baemin_clone_android.src.login.register.interfaces

import com.professionalandroid.apps.baemin_clone_android.NewUserInfo
import com.professionalandroid.apps.baemin_clone_android.email
import com.professionalandroid.apps.baemin_clone_android.src.login.models.DefaultResponse
import com.professionalandroid.apps.baemin_clone_android.src.login.register.models.EmailResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterRetrofitInterface {
    // Check duplication of email
    @POST("/duplicate-email")
    fun checkDuplicateEmail(
        @Body data: email
    ): Call<EmailResponse>

    // Register new ID
    @POST("/signup")
    fun registerNewId(
        @Body data: NewUserInfo
    ): Call<DefaultResponse>
}