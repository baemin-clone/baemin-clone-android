package com.professionalandroid.apps.baemin_clone_android.src.myinfoFragment.modifyMyInfo

import android.app.Application
import android.util.Log
import com.professionalandroid.apps.baemin_clone_android.src.ApplicationClass
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity.Companion.login_status
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity.Companion.user_status
import com.professionalandroid.apps.baemin_clone_android.src.myinfoFragment.modifyMyInfo.interfaces.ModifyMyinfoRetrofitInterface
import com.professionalandroid.apps.baemin_clone_android.src.myinfoFragment.modifyMyInfo.models.WithdrawalResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class ModifyMyinfoService(private val mModifyMyinfoFragment: ModifyMyinfoFragment) {
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
                if(response.body()!!.code == 1){
                   mModifyMyinfoFragment.deletejwt()

                }
            }

        })
    }
}