package com.professionalandroid.apps.baemin_clone_android.src.myinfo.modifyMyInfo

import android.util.Log
import com.professionalandroid.apps.baemin_clone_android.InfoChange
import com.professionalandroid.apps.baemin_clone_android.src.ApplicationClass
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity.Companion.login_status
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity.Companion.user_status
import com.professionalandroid.apps.baemin_clone_android.src.myinfo.modifyMyInfo.interfaces.ModifyMyinfoFragmentView
import com.professionalandroid.apps.baemin_clone_android.src.myinfo.modifyMyInfo.interfaces.ModifyMyinfoRetrofitInterface
import com.professionalandroid.apps.baemin_clone_android.src.myinfo.modifyMyInfo.models.InfoChangeResponse
import com.professionalandroid.apps.baemin_clone_android.src.myinfo.modifyMyInfo.models.ProfilePictureResponse
import com.professionalandroid.apps.baemin_clone_android.src.myinfo.modifyMyInfo.models.UserInfoResponse
import com.professionalandroid.apps.baemin_clone_android.src.myinfo.modifyMyInfo.models.WithdrawalResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ModifyMyinfoService(private val mModifyMyinfoFragmentView: ModifyMyinfoFragmentView) {
    val modifyMyinfoRetrofitInterface: ModifyMyinfoRetrofitInterface = ApplicationClass.retrofitService()!!.create(ModifyMyinfoRetrofitInterface::class.java)

    fun withdrawal(){
        modifyMyinfoRetrofitInterface.withdrawal().enqueue(object : Callback<WithdrawalResponse>{
            override fun onFailure(call: Call<WithdrawalResponse>, t: Throwable) {
                Log.d("test", "실패")
            }
            override fun onResponse(
                call: Call<WithdrawalResponse>,
                response: Response<WithdrawalResponse>
            ) {
                Log.d("test", response.body().toString())
                if(response.body()!!.code == 1){
                    user_status = false
                    login_status = true
                   mModifyMyinfoFragmentView.deletejwt()
                }
            }
        })
    }

    fun getInfo(){
        modifyMyinfoRetrofitInterface.getinfo().enqueue(object : Callback<UserInfoResponse>{
            override fun onFailure(call: Call<UserInfoResponse>, t: Throwable) {
                Log.d("test", "실패")
            }

            override fun onResponse(
                call: Call<UserInfoResponse>,
                response: Response<UserInfoResponse>
            ) {
                val body = response.body()
                Log.d("test", body.toString())
                if(body?.code == 200){
                    mModifyMyinfoFragmentView.putUserdata(body.result.nickname, body.result.profilePath, body.result.email, body.result.phone)
                }
            }

        })
    }

    fun savephoto(imgFile: MultipartBody.Part){
        modifyMyinfoRetrofitInterface.saveProfilePicture(imgFile).enqueue(object : Callback<ProfilePictureResponse>{
            override fun onFailure(call: Call<ProfilePictureResponse>, t: Throwable) {
                Log.d("test", "save photo 실패")
            }

            override fun onResponse(
                call: Call<ProfilePictureResponse>,
                response: Response<ProfilePictureResponse>
            ) {
                val body = response.body()
                Log.d("test", body.toString())

            }

        })
    }

    fun change(data:InfoChange){
        modifyMyinfoRetrofitInterface.changeInfo(data).enqueue(object : Callback<InfoChangeResponse>{
            override fun onFailure(call: Call<InfoChangeResponse>, t: Throwable) {
                Log.d("test", "infochange 실패")
            }

            override fun onResponse(
                call: Call<InfoChangeResponse>,
                response: Response<InfoChangeResponse>
            ) {
                val body = response.body()
                Log.d("test", body.toString())

            }

        })
    }


}