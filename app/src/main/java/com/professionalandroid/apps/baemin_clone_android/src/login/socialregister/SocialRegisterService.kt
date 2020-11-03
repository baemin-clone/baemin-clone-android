package com.professionalandroid.apps.baemin_clone_android.src.login.socialregister

import com.professionalandroid.apps.baemin_clone_android.src.ApplicationClass
import com.professionalandroid.apps.baemin_clone_android.src.login.socialregister.interfaces.SocialRegisterRetrofitInterface
import retrofit2.create

class SocialRegisterService {
    val mSocialRegisterRetrofitInterface: SocialRegisterRetrofitInterface =
        ApplicationClass.retrofitService()!!.create(SocialRegisterRetrofitInterface::class.java)

    fun registerNewSocialId(){

    }

}