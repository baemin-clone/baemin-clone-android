package com.professionalandroid.apps.baemin_clone_android.src.splash

import android.content.Intent
import android.os.Bundle
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import com.professionalandroid.apps.baemin_clone_android.src.ApplicationClass.Companion.X_ACCESS_TOKEN
import com.professionalandroid.apps.baemin_clone_android.src.ApplicationClass.Companion.sSharedPreferences
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity.Companion.login_status
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity.Companion.user_address
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity.Companion.user_status
import com.professionalandroid.apps.baemin_clone_android.src.splash.interfaces.SplashActivityView
import com.professionalandroid.apps.baemin_clone_android.src.splash.model.SplashResponse

class SplashActivity : AppCompatActivity(), SplashActivityView {
    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(sSharedPreferences?.getString(X_ACCESS_TOKEN, null) != null){
            val splashService = SplashService(this)
            splashService.autoLogin()
        }
        else{
            unavailable()
        }

    }

    override fun autoLogin(body: SplashResponse) {
        user_status = true
        login_status = false
        val intent = Intent(this, MainActivity::class.java)
        if(body.info?.address != "") {
            user_address = body.info?.address
        }
        startActivity(intent)
        finish()
    }

    fun unavailable(){
        user_status = false
        login_status = false
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}