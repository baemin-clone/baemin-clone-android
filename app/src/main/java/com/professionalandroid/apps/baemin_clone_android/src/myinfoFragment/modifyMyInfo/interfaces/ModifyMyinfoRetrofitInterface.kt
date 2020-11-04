package com.professionalandroid.apps.baemin_clone_android.src.myinfoFragment.modifyMyInfo.interfaces

import com.professionalandroid.apps.baemin_clone_android.src.myinfoFragment.modifyMyInfo.models.WithdrawalResponse
import retrofit2.Call
import retrofit2.http.DELETE

interface ModifyMyinfoRetrofitInterface {
    @DELETE("/signout")
    fun withdrawal(

    ): Call<WithdrawalResponse>
}