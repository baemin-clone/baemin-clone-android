package com.professionalandroid.apps.baemin_clone_android.src

import android.app.Application
import android.content.SharedPreferences
import com.professionalandroid.apps.baemin_clone_android.config.XAccessTokenInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class ApplicationClass : Application() {
    override fun onCreate() {
        super.onCreate()
        if (sSharedPreferences == null) {
            sSharedPreferences = applicationContext.getSharedPreferences(TAG, MODE_PRIVATE)
        }
    }

    companion object {

        // 테스트 서버 주소
        var BASE_URL = "https://dev.seonuk.shop"

        // 실서버 주소
        //    public static String BASE_URL = "https://template.softsquared.com/";

        var sSharedPreferences: SharedPreferences? = null

        // SharedPreferences 키 값
        var TAG = "TEMPLATE_APP"

        // JWT Token 값
        var X_ACCESS_TOKEN = "X-ACCESS-TOKEN"

        //날짜 형식
        var DATE_FORMAT = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)

        // Retrofit 인스턴스
        var retrofit: Retrofit? = null
        fun retrofitService(): Retrofit? {
            if (retrofit == null) {
                val client: OkHttpClient = OkHttpClient.Builder()
                    .readTimeout(5000, TimeUnit.MILLISECONDS)
                    .connectTimeout(5000, TimeUnit.MILLISECONDS)
                    .addNetworkInterceptor(XAccessTokenInterceptor()) // JWT 자동 헤더 전송
                    .build()
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }

        fun NaverRetrofitService(): Retrofit? {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://naveropenapi.apigw.ntruss.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit
        }
    }
}