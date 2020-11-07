package com.professionalandroid.apps.baemin_clone_android.src.login

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity.Companion.login_status
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity.Companion.user_status
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.UserIdSet
import com.professionalandroid.apps.baemin_clone_android.src.ApplicationClass.Companion.X_ACCESS_TOKEN
import com.professionalandroid.apps.baemin_clone_android.src.ApplicationClass.Companion.sSharedPreferences
import com.professionalandroid.apps.baemin_clone_android.src.BaseActivity
import com.professionalandroid.apps.baemin_clone_android.src.login.interfaces.LoginActivityView
import kotlinx.android.synthetic.main.activity_login_page.*


class LoginActivity : BaseActivity(), LoginActivityView {

    val loginService = LoginService(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        login_register.setOnClickListener {
            val agreementPage = AgreementFragment()
            addFragment(agreementPage.apply {
                arguments = Bundle().apply {
                    putString("kinds", "general")
                }
            })
        }

        // general login
        login_submit_btn.setOnClickListener {
            val data =
                UserIdSet(
                    login_id.text.toString(),
                    login_password.text.toString()
                )
            loginService.login(data)
        }

        // Naver login
        login_naver_btn.setOnClickListener {
            loginService.naverLoginApi(this)
        }
    }

    fun addFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.login_main_layout, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun closeFragemtn(fragment: Fragment){
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.remove(fragment)
        transaction.commit()
        manager.popBackStack()
    }

    override fun successLogin() {
        user_status = true
        login_status = true
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finishAffinity()
    }

    override fun isAlreadyRegistered(code: Int, token: String) {
        when(code){
            // Existing User
            7 -> {
                Log.d("test", "이미 가입된 유저")
                successLogin()
            }
            // New User
            1 -> {
                Log.d("test","새로운 유저")
                val agreementPage = AgreementFragment()
                addFragment(agreementPage.apply {
                    arguments = Bundle().apply {
                        putString("kinds", "naver")
                        putString("token", token)
                    }
                })
            }
        }
    }

    @SuppressLint("CommitPrefEdits")
    override fun saveJwt(jwt: String) {
        sSharedPreferences!!.edit().apply{
            putString(X_ACCESS_TOKEN, jwt)
            apply()
        }
    }
}