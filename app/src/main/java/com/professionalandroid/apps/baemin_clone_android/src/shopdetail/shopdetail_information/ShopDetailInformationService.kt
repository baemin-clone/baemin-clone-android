package com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_information

import android.util.Log
import com.professionalandroid.apps.baemin_clone_android.src.ApplicationClass
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_information.interfaces.ShopDetailInformationRetrofitInterface
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_information.interfaces.ShopDetailInformationFragmentView
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_information.models.ShopDetailInformationResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShopDetailInformationService(val mShopDetailInformationFragmentView: ShopDetailInformationFragmentView) {
    val mShopDetailInformationRetrofitInterface: ShopDetailInformationRetrofitInterface =
        ApplicationClass.retrofitService()!!.create(ShopDetailInformationRetrofitInterface::class.java)
    fun shopDetailInformation(idx: Int){
        mShopDetailInformationRetrofitInterface.shopDetailInformation(idx).enqueue(object : Callback<ShopDetailInformationResponse>{
            override fun onFailure(call: Call<ShopDetailInformationResponse>, t: Throwable) {
                Log.d("test", "shopdetailinformation 실패")
            }

            override fun onResponse(
                call: Call<ShopDetailInformationResponse>,
                response: Response<ShopDetailInformationResponse>
            ) {
                val body = response.body()
                Log.d("test", body.toString())
                if(body?.code == 0){
                    mShopDetailInformationFragmentView.shopDetailInformation(body)
                }
            }

        })
    }
}