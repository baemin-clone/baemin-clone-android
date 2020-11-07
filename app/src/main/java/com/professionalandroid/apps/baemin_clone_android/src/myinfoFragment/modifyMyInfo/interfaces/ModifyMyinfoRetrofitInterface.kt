package com.professionalandroid.apps.baemin_clone_android.src.myinfoFragment.modifyMyInfo.interfaces

import com.professionalandroid.apps.baemin_clone_android.src.myinfoFragment.modifyMyInfo.models.UserInfoResponse
import com.professionalandroid.apps.baemin_clone_android.src.myinfoFragment.modifyMyInfo.models.WithdrawalResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Query

interface ModifyMyinfoRetrofitInterface {
    @GET("/user-info")
    fun getinfo(
    ): Call<UserInfoResponse>

    @DELETE("/signout")
    fun withdrawal(
    ): Call<WithdrawalResponse>
}