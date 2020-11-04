package com.professionalandroid.apps.baemin_clone_android.src.login.register

import android.util.Log
import com.professionalandroid.apps.baemin_clone_android.NewUserInfo
import com.professionalandroid.apps.baemin_clone_android.email
import com.professionalandroid.apps.baemin_clone_android.src.ApplicationClass
import com.professionalandroid.apps.baemin_clone_android.src.login.models.DefaultResponse
import com.professionalandroid.apps.baemin_clone_android.src.login.register.interfaces.RegisterRetrofitInterface
import com.professionalandroid.apps.baemin_clone_android.src.login.register.models.EmailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterService(private val mRegisterFragment: RegisterFragment) {
    val mRegisterRetrofitInterface: RegisterRetrofitInterface =
        ApplicationClass.retrofitService()!!.create(RegisterRetrofitInterface::class.java)

    // check duplication of email
    fun checkDuplication(data: email){
        mRegisterRetrofitInterface.checkDuplicateEmail(data).enqueue(object : Callback<EmailResponse>{
            override fun onFailure(call: Call<EmailResponse>, t: Throwable) {
                Log.d("test", "실패")
            }

            override fun onResponse(call: Call<EmailResponse>, response: Response<EmailResponse>) {
                Log.d("test", response.toString())
                Log.d("test", response.body().toString())
                if (response.body()!!.code == 2) {
                    mRegisterFragment.availableEmail()
                }
            }

        })
    }

    // register new ID
    fun registerNewId(data: NewUserInfo){
        Log.d("전달 값", data.toString())
        mRegisterRetrofitInterface.registerNewId(data).enqueue(object: Callback<DefaultResponse>{
            override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                Log.d("test", "실패")
            }

            override fun onResponse(
                call: Call<DefaultResponse>,
                response: Response<DefaultResponse>
            ) {
                Log.d("서버 반환값", response.body().toString())
                if(response.body()?.code == 1){
                    mRegisterFragment.saveJwt(response.body()?.result?.jwt!!)
                    mRegisterFragment.successRegister()
                }
            }

        })
    }
}