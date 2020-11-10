package com.professionalandroid.apps.baemin_clone_android.src.Shoplist.ShoplistViewObject

import android.util.Log
import com.professionalandroid.apps.baemin_clone_android.src.ApplicationClass
import com.professionalandroid.apps.baemin_clone_android.src.Shoplist.ShoplistViewObject.interfaces.ShoplistViewObjectFragmentView
import com.professionalandroid.apps.baemin_clone_android.src.Shoplist.ShoplistViewObject.interfaces.ShoplistViewObjectRetrofitInterface
import com.professionalandroid.apps.baemin_clone_android.src.Shoplist.ShoplistViewObject.models.ShoplistObjectResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShoplistViewObjectService(private val mShoplistViewObjectFragmentView: ShoplistViewObjectFragmentView) {
    val mShoplistViewObjectRetrofitInterface: ShoplistViewObjectRetrofitInterface =
        ApplicationClass.retrofitService()!!.create(ShoplistViewObjectRetrofitInterface::class.java)

//    fun getfirstShoplist(shoplist: MutableList<Result>, category: String, order: String?, minAmount: Int?, tip: Int?, star: Double?, page: Int, size: Int){
//        mShoplistViewObjectRetrofitInterface.getShoplist(category,order, minAmount, tip, star, page, size).enqueue(object : Callback<ShoplistObjectResponse>{
//            override fun onFailure(call: Call<ShoplistObjectResponse>, t: Throwable) {
//                Log.d("test", "실패")
//            }
//
//            override fun onResponse(
//                call: Call<ShoplistObjectResponse>,
//                response: Response<ShoplistObjectResponse>
//            ) {
//                TODO("Not yet implemented")
//            }
//
//        })
//    }

    fun getmoreShoplist(category: String, order: String?, minAmount: Int?, tip: Int?, star: Double?, page: Int, size: Int) {
        mShoplistViewObjectRetrofitInterface.getShoplist(category,order, minAmount, tip, star, page, size).enqueue(object : Callback<ShoplistObjectResponse>{
            override fun onFailure(call: Call<ShoplistObjectResponse>, t: Throwable) {
                Log.d("test", "실패")
            }

            override fun onResponse(
                call: Call<ShoplistObjectResponse>,
                response: Response<ShoplistObjectResponse>
            ) {
                val body = response.body()
                val templist = body?.result
                Log.d("test", body.toString())
                mShoplistViewObjectFragmentView.loadmore(templist)
            }
        })

    }
}