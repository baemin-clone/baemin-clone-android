package com.professionalandroid.apps.baemin_clone_android.src.login.recognize

import android.util.Log
import com.professionalandroid.apps.baemin_clone_android.PhoneNumber
import com.professionalandroid.apps.baemin_clone_android.PhoneRecognization
import com.professionalandroid.apps.baemin_clone_android.src.ApplicationClass
import com.professionalandroid.apps.baemin_clone_android.src.login.recognize.interfaces.RecognizeFragmentView
import com.professionalandroid.apps.baemin_clone_android.src.login.recognize.interfaces.RecognizeInterfaces
import com.professionalandroid.apps.baemin_clone_android.src.login.recognize.models.SMSResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecognizeService(val mRecognizeFragmentView: RecognizeFragmentView) {
    val mRecognizeInterfaces: RecognizeInterfaces =
        ApplicationClass.retrofitService()!!.create(RecognizeInterfaces::class.java)

    fun authSMS(phone: PhoneNumber){
        mRecognizeInterfaces.authSMS(phone).enqueue(object : Callback<SMSResponse>{
            override fun onFailure(call: Call<SMSResponse>, t: Throwable) {
                Log.d("test", "SMS실패")
            }

            override fun onResponse(call: Call<SMSResponse>, response: Response<SMSResponse>) {
                val body = response.body()
                Log.d("test", body.toString())
                if (body?.code == 200) {
                    mRecognizeFragmentView.authSMS()
                }
            }
        })
    }

    fun authNumber(data: PhoneRecognization){
        mRecognizeInterfaces.authNumber(data).enqueue(object : Callback<SMSResponse>{
            override fun onFailure(call: Call<SMSResponse>, t: Throwable) {
                Log.d("test", "authNumber 실패")
            }

            override fun onResponse(call: Call<SMSResponse>, response: Response<SMSResponse>) {
                val body = response.body()
                Log.d("test", body.toString())
                if(body?.code == 200){
                    mRecognizeFragmentView.authNumber()
                }
            }

        })

    }
}