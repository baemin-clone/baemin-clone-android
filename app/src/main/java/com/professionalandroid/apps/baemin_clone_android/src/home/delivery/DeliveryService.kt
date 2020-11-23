package com.professionalandroid.apps.baemin_clone_android.src.home.delivery

import android.util.Log
import com.professionalandroid.apps.baemin_clone_android.src.ApplicationClass
import com.professionalandroid.apps.baemin_clone_android.src.home.delivery.interfaces.DeliveryFragmentView
import com.professionalandroid.apps.baemin_clone_android.src.home.delivery.interfaces.DeliveryInterface
import com.professionalandroid.apps.baemin_clone_android.src.home.delivery.models.DeliveryResponse
import com.professionalandroid.apps.baemin_clone_android.src.home.delivery.models.RecommendationResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeliveryService(val mDeliveryFragmentView: DeliveryFragmentView) {
    val mDeliveryInterface: DeliveryInterface =
        ApplicationClass.retrofitService()!!.create(DeliveryInterface::class.java)

    fun brand() {
        mDeliveryInterface.brand().enqueue(object : Callback<DeliveryResponse> {
            override fun onFailure(call: Call<DeliveryResponse>, t: Throwable) {
                Log.d("test", "delivery brand 실패")
            }

            override fun onResponse(
                call: Call<DeliveryResponse>,
                response: Response<DeliveryResponse>
            ) {
                val body = response.body()
                if (body?.code == 200) {
                    mDeliveryFragmentView.brand(body)
                }
            }

        })
    }

    fun recommendation1(tag: String) {
        mDeliveryInterface.recommendation(tag).enqueue(object : Callback<RecommendationResponse> {
            override fun onFailure(call: Call<RecommendationResponse>, t: Throwable) {
                Log.d("test", "recommendation 실패")
            }

            override fun onResponse(
                call: Call<RecommendationResponse>,
                response: Response<RecommendationResponse>
            ) {
                val body = response.body()
                Log.d("test", body.toString())

                if (body?.code == 200) {
                    mDeliveryFragmentView.recommendation1(body)
                }
            }

        })
    }

    fun recommendation2(tag: String) {
        mDeliveryInterface.recommendation(tag).enqueue(object : Callback<RecommendationResponse> {
            override fun onFailure(call: Call<RecommendationResponse>, t: Throwable) {
                Log.d("test", "recommendation 실패")
            }

            override fun onResponse(
                call: Call<RecommendationResponse>,
                response: Response<RecommendationResponse>
            ) {
                val body = response.body()
                Log.d("test", body.toString())

                if (body?.code == 200) {
                    mDeliveryFragmentView.recommendation2(body)
                }
            }

        })
    }
}