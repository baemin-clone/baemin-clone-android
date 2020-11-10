package com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_menu

import android.util.Log
import com.professionalandroid.apps.baemin_clone_android.src.ApplicationClass
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_menu.interfaces.ShopDetailMenuFragmentView
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_menu.interfaces.ShopDetailMenuRetrofitInterface
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_menu.models.ShopDetailMenuResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShopDetailMenuService(val mShopDetailMenuFragmentView: ShopDetailMenuFragmentView) {
    val mShopDetailMenuRetrofitInterface: ShopDetailMenuRetrofitInterface =
        ApplicationClass.retrofitService()!!.create(ShopDetailMenuRetrofitInterface::class.java)

    fun shopDetailMenu(idx: Int){
        mShopDetailMenuRetrofitInterface.shopDetailMenu(idx).enqueue(object : Callback<ShopDetailMenuResponse>{
            override fun onFailure(call: Call<ShopDetailMenuResponse>, t: Throwable) {
                Log.d("test", "실패")
            }

            override fun onResponse(
                call: Call<ShopDetailMenuResponse>,
                response: Response<ShopDetailMenuResponse>
            ) {
                val body = response.body()
                Log.d("test", body.toString())
                if (body?.code == 200) {
                    mShopDetailMenuFragmentView.shopDetailMenu(body)
                }
            }
        })
    }
}