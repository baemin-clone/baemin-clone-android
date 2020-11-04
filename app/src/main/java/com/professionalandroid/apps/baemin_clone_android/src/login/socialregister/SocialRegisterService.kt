package com.professionalandroid.apps.baemin_clone_android.src.login.socialregister

import android.util.Log
import com.professionalandroid.apps.baemin_clone_android.NewSocialUserInfo
import com.professionalandroid.apps.baemin_clone_android.src.ApplicationClass
import com.professionalandroid.apps.baemin_clone_android.src.login.socialregister.interfaces.SocialRegisterFragmentView
import com.professionalandroid.apps.baemin_clone_android.src.login.socialregister.interfaces.SocialRegisterRetrofitInterface
import com.professionalandroid.apps.baemin_clone_android.src.login.socialregister.models.SocialResponse
import retrofit2.Call
import retrofit2.Response

class SocialRegisterService(private var mSocialRegisterFragmentView: SocialRegisterFragmentView) {
    val mSocialRegisterRetrofitInterface: SocialRegisterRetrofitInterface =
        ApplicationClass.retrofitService()!!.create(SocialRegisterRetrofitInterface::class.java)

    fun registerNewSocialId(data: NewSocialUserInfo){
        mSocialRegisterRetrofitInterface.socialLogin(data).enqueue(object : retrofit2.Callback<SocialResponse>{
            override fun onFailure(call: Call<SocialResponse>, t: Throwable) {
                Log.d("test", "실패")
            }

            override fun onResponse(
                call: Call<SocialResponse>,
                response: Response<SocialResponse>
            ) {
                TODO("Not yet implemented")
            }

        })
    }

}