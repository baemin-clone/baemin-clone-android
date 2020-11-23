package com.professionalandroid.apps.baemin_clone_android.src.myinfo.modifyMyInfo.interfaces

import com.professionalandroid.apps.baemin_clone_android.InfoChange
import com.professionalandroid.apps.baemin_clone_android.src.myinfo.modifyMyInfo.models.InfoChangeResponse
import com.professionalandroid.apps.baemin_clone_android.src.myinfo.modifyMyInfo.models.ProfilePictureResponse
import com.professionalandroid.apps.baemin_clone_android.src.myinfo.modifyMyInfo.models.UserInfoResponse
import com.professionalandroid.apps.baemin_clone_android.src.myinfo.modifyMyInfo.models.WithdrawalResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ModifyMyinfoRetrofitInterface {
    @GET("/user-info")
    fun getinfo(
    ): Call<UserInfoResponse>

    @DELETE("/signout")
    fun withdrawal(
    ): Call<WithdrawalResponse>

    @Multipart
    @PATCH("/profile")
    fun saveProfilePicture(
        @Part img: MultipartBody.Part
    ): Call<ProfilePictureResponse>

    @PATCH("/user-info")
    fun changeInfo(
        @Body data: InfoChange
    ): Call<InfoChangeResponse>
}