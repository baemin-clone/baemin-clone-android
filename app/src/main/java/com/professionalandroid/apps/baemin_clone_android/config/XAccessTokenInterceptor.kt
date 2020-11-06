package com.professionalandroid.apps.baemin_clone_android.config

import android.content.Context
import android.util.Log
import com.professionalandroid.apps.baemin_clone_android.src.ApplicationClass.Companion.X_ACCESS_TOKEN
import com.professionalandroid.apps.baemin_clone_android.src.ApplicationClass.Companion.sSharedPreferences
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class XAccessTokenInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        val jwtToken: String? = sSharedPreferences?.getString(X_ACCESS_TOKEN, null)
        if (jwtToken != null) {
            builder.addHeader("x-access-token", jwtToken)
        }
        return chain.proceed(builder.build())
    }
}