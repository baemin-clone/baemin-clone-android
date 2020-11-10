package com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_visit

import android.util.Log
import com.professionalandroid.apps.baemin_clone_android.src.ApplicationClass
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_visit.interfaces.ShopDetailVisitFragmentView
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_visit.interfaces.ShopDetailVisitRetrofitInterface
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_visit.models.ShopDetailVisitResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShopDetailVisitService(val mShopDetailVisitFragmentView: ShopDetailVisitFragmentView) {
    val mShopDetailVisitRetrofitInterface: ShopDetailVisitRetrofitInterface =
        ApplicationClass.retrofitService()!!.create(ShopDetailVisitRetrofitInterface::class.java)

    fun  shopDetailVisit(idx: Int){
        mShopDetailVisitRetrofitInterface.shopDetailVisit(idx).enqueue(object : Callback<ShopDetailVisitResponse>{
            override fun onFailure(call: Call<ShopDetailVisitResponse>, t: Throwable) {
                Log.d("test", "실패")
            }

            override fun onResponse(
                call: Call<ShopDetailVisitResponse>,
                response: Response<ShopDetailVisitResponse>
            ) {
                val body = response.body()
                Log.d("test", body.toString())
                if(body?.code == 200){
                    mShopDetailVisitFragmentView.shopDetailVisit(body.result)
                }
            }

        })
    }
}