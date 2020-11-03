package com.professionalandroid.apps.baemin_clone_android.src.login

import android.util.Log
import com.professionalandroid.apps.baemin_clone_android.UserIdSet
import com.professionalandroid.apps.baemin_clone_android.socailLoginToken
import com.professionalandroid.apps.baemin_clone_android.src.ApplicationClass
import com.professionalandroid.apps.baemin_clone_android.src.login.interfaces.LoginActivityView
import com.professionalandroid.apps.baemin_clone_android.src.login.interfaces.LoginRetrofitInterface
import com.professionalandroid.apps.baemin_clone_android.src.login.models.DefaultResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginService(private val mLoginActivityView: LoginActivityView) {
    val mloginRetrofitInterface: LoginRetrofitInterface =
        ApplicationClass.retrofitService()!!.create(LoginRetrofitInterface::class.java)

    // general login
    fun login(data: UserIdSet){
        mloginRetrofitInterface.login(data).enqueue(object : Callback<DefaultResponse>{
            override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                Log.d("test", "실패")
            }

            override fun onResponse(
                call: Call<DefaultResponse>,
                response: Response<DefaultResponse>
            ) {
                if(response.body()?.code == 1){
                    Log.d("test", response.body().toString())
                    mLoginActivityView.successLogin()
                }
            }
        })
    }

    // pass on token to server from naver
    fun passOnToken(data: socailLoginToken){
        mloginRetrofitInterface.socialLogin(data).enqueue(object : Callback<DefaultResponse>{
            override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                Log.d("test", "실패")
            }
            override fun onResponse(
                call: Call<DefaultResponse>,
                response: Response<DefaultResponse>
            ) {
                Log.d("test", response.body().toString())
                mLoginActivityView.isAlreadyRegistered(response.body()!!.code)
            }

        })
    }
}