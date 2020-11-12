package com.professionalandroid.apps.baemin_clone_android.src.shopping_cart

import android.util.Log
import com.professionalandroid.apps.baemin_clone_android.Order
import com.professionalandroid.apps.baemin_clone_android.ShoppingCart
import com.professionalandroid.apps.baemin_clone_android.src.ApplicationClass
import com.professionalandroid.apps.baemin_clone_android.src.shopping_cart.interfaces.ShoppingCartFragmentView
import com.professionalandroid.apps.baemin_clone_android.src.shopping_cart.interfaces.ShoppingCartRetrofitInterface
import com.professionalandroid.apps.baemin_clone_android.src.shopping_cart.models.OrderResponse
import com.professionalandroid.apps.baemin_clone_android.src.shopping_cart.models.ShoppingCartItemResponse
import com.professionalandroid.apps.baemin_clone_android.src.shopping_cart.models.ShoppingCartShopResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShoppingCartService (val mShoppingCartFragmentView: ShoppingCartFragmentView){
    val mShoppingCartRetrofitInterface: ShoppingCartRetrofitInterface =
        ApplicationClass.retrofitService()!!.create(ShoppingCartRetrofitInterface::class.java)

    fun shoppingCartTitle(idx:Int){
        mShoppingCartRetrofitInterface.shoppingCartTitle(idx).enqueue(object : Callback<ShoppingCartShopResponse>{
            override fun onFailure(call: Call<ShoppingCartShopResponse>, t: Throwable) {
                Log.d("test", "shoppingcarttitle 실패")
            }
            override fun onResponse(
                call: Call<ShoppingCartShopResponse>,
                response: Response<ShoppingCartShopResponse>
            ) {
                val body = response.body()
                Log.d("test", body.toString())
                if(body?.code == 200){
                    mShoppingCartFragmentView.shoppingCartTitle(body)
                }
            }

        })
    }

    fun shoppingCartItem(data: ShoppingCart, num: Int ){
        mShoppingCartRetrofitInterface.shoppingCartItem(data).enqueue(object : Callback<ShoppingCartItemResponse>{
            override fun onFailure(call: Call<ShoppingCartItemResponse>, t: Throwable) {
                Log.d("test", "shoppingcartitem 실패")
            }

            override fun onResponse(
                call: Call<ShoppingCartItemResponse>,
                response: Response<ShoppingCartItemResponse>
            ) {
                val body = response.body()
                Log.d("test", body.toString())
                if(body?.code == 200){
                    mShoppingCartFragmentView.shoppingCartItem(body, num)
                }
            }

        })
    }

    fun order(data: Order){
        mShoppingCartRetrofitInterface.order(data).enqueue(object : Callback<OrderResponse>{
            override fun onFailure(call: Call<OrderResponse>, t: Throwable) {
                Log.d("test", "order 실패")
            }

            override fun onResponse(call: Call<OrderResponse>, response: Response<OrderResponse>) {
                val body = response.body()
                Log.d("test", body.toString())
            }

        })
    }

}