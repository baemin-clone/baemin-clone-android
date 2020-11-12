package com.professionalandroid.apps.baemin_clone_android.src.shopdetail

import android.util.Log
import com.professionalandroid.apps.baemin_clone_android.src.ApplicationClass
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.interfaces.ShopDetailFragmentView
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.interfaces.ShopDetailRetrofitInterface
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.models.BookmarkResponse
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.models.ShopDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShopDetailService (val mShopDetailFragmentView: ShopDetailFragmentView){
    val mShopDetailRetrofitInterface: ShopDetailRetrofitInterface =
        ApplicationClass.retrofitService()!!.create(ShopDetailRetrofitInterface::class.java)

    fun getShopDetail(idx: Int){
        mShopDetailRetrofitInterface.getShopDetail(idx).enqueue(object : Callback<ShopDetailResponse>{
            override fun onFailure(call: Call<ShopDetailResponse>, t: Throwable) {
                Log.d("test", "ShopDetail 실패")
            }

            override fun onResponse(
                call: Call<ShopDetailResponse>,
                response: Response<ShopDetailResponse>
            ) {
                val body = response.body()
                Log.d("test", body.toString())
                if (body?.code == 200) {
                    mShopDetailFragmentView.shopDetail(body.result)
                }
            }

        })
    }

    fun modifyBookmark(idx: Int){
        mShopDetailRetrofitInterface.modifyBookmark(idx).enqueue(object : Callback<BookmarkResponse>{
            override fun onFailure(call: Call<BookmarkResponse>, t: Throwable) {
                Log.d("test", "bookmark 실패")
            }

            override fun onResponse(
                call: Call<BookmarkResponse>,
                response: Response<BookmarkResponse>
            ) {
                val body = response.body()
                Log.d("test", body.toString())
            }

        })
    }
}