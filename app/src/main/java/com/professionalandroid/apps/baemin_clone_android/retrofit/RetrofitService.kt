package com.professionalandroid.apps.baemin_clone_android.retrofit

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RetrofitService {

    // 회원 가입 시 유저 이메일 중복확인
    @POST("/duplicate-email")
    fun checkDuplicateEmail(
        @Body data: email
    ): Call<email_response>

    // 회원 가입
    @POST("/signup")
    fun registerNewId(
        @Body data: NewUserInfo
    ): Call<NewUserInfo_response>
}