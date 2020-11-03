package com.professionalandroid.apps.baemin_clone_android.src.login

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.OAuthLoginHandler
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity.Companion.login_status
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity.Companion.user_status
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.UserIdSet
import com.professionalandroid.apps.baemin_clone_android.socailLoginToken
import com.professionalandroid.apps.baemin_clone_android.src.BaseActivity
import com.professionalandroid.apps.baemin_clone_android.src.login.interfaces.LoginActivityView
import kotlinx.android.synthetic.main.activity_login_page.*


class LoginActivity : BaseActivity(), LoginActivityView {

    val loginService = LoginService(this)
    lateinit var mOAuthLoginModule: OAuthLogin

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
            mOAuthLoginModule = OAuthLogin.getInstance()
            mOAuthLoginModule.init(
                this
                , getString(R.string.naver_client_id)
                , getString(R.string.naver_client_secret)
                , getString(R.string.naver_client_name) //,OAUTH_CALLBACK_INTENT
            )
            @SuppressLint("HandlerLeak")
            val mOAuthLoginHandler: OAuthLoginHandler =
                object : OAuthLoginHandler() {
                    override fun run(success: Boolean) {
                        if (success) {
                            val accessToken =
                                mOAuthLoginModule.getAccessToken(this@LoginActivity)
                            val refreshToken =
                                mOAuthLoginModule.getRefreshToken(this@LoginActivity)
                            val expiresAt = mOAuthLoginModule.getExpiresAt(this@LoginActivity)
                            val tokenType = mOAuthLoginModule.getTokenType(this@LoginActivity)
                            Log.i("LoginData", "accessToken : $accessToken")
                            Log.i("LoginData", "refreshToken : $refreshToken")
                            Log.i("LoginData", "expiresAt : $expiresAt")
                            Log.i("LoginData", "tokenType : $tokenType")

                            // pass on accessToken to server
                            val data =
                                socailLoginToken(
                                    accessToken.toString()
                                )
                            loginService.passOnToken(data)


                        } else {
                            val errorCode = mOAuthLoginModule
                                .getLastErrorCode(this@LoginActivity).code
                            val errorDesc =
                                mOAuthLoginModule.getLastErrorDesc(this@LoginActivity)
                            Toast.makeText(
                                this@LoginActivity, "errorCode:" + errorCode
                                        + ", errorDesc:" + errorDesc, Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            mOAuthLoginModule.startOauthLoginActivity(this@LoginActivity, mOAuthLoginHandler);
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
    }

    override fun isAlreadyRegistered(code: Int) {
        when(code){
            // Existing User
            1 -> {
                Log.d("test", "메인으로")
                successLogin()
            }
            // New User
            200 -> {
                Log.d("test","동의로")
                val agreementPage = AgreementFragment()
                addFragment(agreementPage.apply {
                    arguments = Bundle().apply {
                        putString("kinds", "naver")
                    }
                })
            }
        }
    }
}