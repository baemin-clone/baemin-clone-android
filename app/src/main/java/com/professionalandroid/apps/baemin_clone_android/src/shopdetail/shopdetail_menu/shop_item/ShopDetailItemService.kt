package com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_menu.shop_item

import android.util.Log
import com.professionalandroid.apps.baemin_clone_android.src.ApplicationClass
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_menu.shop_item.interfaces.ShopDetailItemFragmentView
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_menu.shop_item.interfaces.ShopDetailItemRetrofitInterfaces
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_menu.shop_item.models.ShopDetailItemResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShopDetailItemService(val mShopDetailItemFragmentView: ShopDetailItemFragmentView) {
    val mShopDetailItemRetrofitInterfaces: ShopDetailItemRetrofitInterfaces =
        ApplicationClass.retrofitService()!!.create(ShopDetailItemRetrofitInterfaces::class.java)

    fun shopDetailItem(idx: Int){
        mShopDetailItemRetrofitInterfaces.shopDetailItem(idx).enqueue(object: Callback<ShopDetailItemResponse>{
            override fun onFailure(call: Call<ShopDetailItemResponse>, t: Throwable) {
                Log.d("test", "shopdetailitem 실패")
            }

            override fun onResponse(
                call: Call<ShopDetailItemResponse>,
                response: Response<ShopDetailItemResponse>
            ) {
                val body = response.body()
                Log.d("test", body.toString())
                if(body?.code == 200){
                    mShopDetailItemFragmentView.shopDetailItem(body)
                }
            }
        })
    }
}