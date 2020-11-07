package com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_delivery

import android.util.Log
import com.professionalandroid.apps.baemin_clone_android.src.ApplicationClass
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_delivery.interfaces.ShopDetailDeliveryFragmentView
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_delivery.interfaces.ShopDetailDeliveryRetrofitInterface
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_delivery.models.ShopDetailDeliveryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShopDetailDeliveryService(val mShopDetailDeliveryFragmentView: ShopDetailDeliveryFragmentView) {
    val mShopDetailDeliveryRetrofitInterface: ShopDetailDeliveryRetrofitInterface =
        ApplicationClass.retrofitService()!!.create(ShopDetailDeliveryRetrofitInterface::class.java)

    fun shopDetailDelivery(idx: Int){
        mShopDetailDeliveryRetrofitInterface.getShopDetailDelivery(idx).enqueue(object : Callback<ShopDetailDeliveryResponse>{
            override fun onFailure(call: Call<ShopDetailDeliveryResponse>, t: Throwable) {
                Log.d("test", "실패")
            }

            override fun onResponse(
                call: Call<ShopDetailDeliveryResponse>,
                response: Response<ShopDetailDeliveryResponse>
            ) {
                val body = response.body()
                Log.d("test", body.toString())
                if(body?.code == 200){
                    mShopDetailDeliveryFragmentView.shopDetailDelivery(body.result)
                }
            }

        })
    }
}