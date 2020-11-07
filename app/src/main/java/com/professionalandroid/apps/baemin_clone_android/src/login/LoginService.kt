package com.professionalandroid.apps.baemin_clone_android.src.login

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.Log
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.OAuthLoginHandler
import com.professionalandroid.apps.baemin_clone_android.R
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
                Log.d("test", response.body().toString())
                if(response.body()?.code == 1){
                    mLoginActivityView.saveJwt(response.body()?.result?.jwt!!)
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
                mLoginActivityView.isAlreadyRegistered(response.body()!!.code, data.accessToken)
            }
        })
    }

    fun naverLoginApi(context: Context) {
        val mOAuthLoginModule = OAuthLogin.getInstance()
        mOAuthLoginModule.init(
            context
            , context.getString(R.string.naver_client_id)
            , context.getString(R.string.naver_client_secret)
            , context.getString(R.string.naver_client_name) //,OAUTH_CALLBACK_INTENT
        )
        @SuppressLint("HandlerLeak")
        val mOAuthLoginHandler: OAuthLoginHandler =
            object : OAuthLoginHandler() {
                override fun run(success: Boolean) {
                    if (success) {
                        val accessToken =
                            mOAuthLoginModule.getAccessToken(context)
                        val refreshToken =
                            mOAuthLoginModule.getRefreshToken(context)
                        val expiresAt = mOAuthLoginModule.getExpiresAt(context)
                        val tokenType = mOAuthLoginModule.getTokenType(context)
                        Log.i("LoginData", "accessToken : $accessToken")
                        Log.i("LoginData", "refreshToken : $refreshToken")
                        Log.i("LoginData", "expiresAt : $expiresAt")
                        Log.i("LoginData", "tokenType : $tokenType")

                        // pass on accessToken to server
                        val data = socailLoginToken(accessToken.toString())
                        passOnToken(data)

                    } else {
                        val errorCode = mOAuthLoginModule
                            .getLastErrorCode(context).code
                        val errorDesc =
                            mOAuthLoginModule.getLastErrorDesc(context)
                    }
                }
            }
        mOAuthLoginModule.startOauthLoginActivity(context as Activity?, mOAuthLoginHandler)
    }
}