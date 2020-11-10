package com.professionalandroid.apps.baemin_clone_android.src.splash

import android.util.Log
import com.professionalandroid.apps.baemin_clone_android.src.ApplicationClass
import com.professionalandroid.apps.baemin_clone_android.src.splash.interfaces.SplashActivityView
import com.professionalandroid.apps.baemin_clone_android.src.splash.interfaces.SplashRetrofitInterface
import com.professionalandroid.apps.baemin_clone_android.src.splash.model.SplashResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashService(val mSplashActivityView: SplashActivityView) {
    val mSplashRetrofitInterface: SplashRetrofitInterface =
        ApplicationClass.retrofitService()!!.create(SplashRetrofitInterface::class.java)

    fun autoLogin(){
        mSplashRetrofitInterface.autoLogin().enqueue(object : Callback<SplashResponse>{
            override fun onFailure(call: Call<SplashResponse>, t: Throwable) {
                Log.d("test", "splash 실패")
            }

            override fun onResponse(
                call: Call<SplashResponse>,
                response: Response<SplashResponse>
            ) {
                val body = response.body()
                Log.d("test", body.toString())
                if(body?.code == 200){
                    mSplashActivityView.autoLogin(body)
                }
            }

        })
    }
}