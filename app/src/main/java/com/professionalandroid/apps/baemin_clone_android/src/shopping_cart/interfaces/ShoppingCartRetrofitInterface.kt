package com.professionalandroid.apps.baemin_clone_android.src.shopping_cart.interfaces

import com.professionalandroid.apps.baemin_clone_android.OptionArray
import com.professionalandroid.apps.baemin_clone_android.Order
import com.professionalandroid.apps.baemin_clone_android.ShoppingCart
import com.professionalandroid.apps.baemin_clone_android.src.shopping_cart.models.OrderResponse
import com.professionalandroid.apps.baemin_clone_android.src.shopping_cart.models.ShoppingCartItem
import com.professionalandroid.apps.baemin_clone_android.src.shopping_cart.models.ShoppingCartItemResponse
import com.professionalandroid.apps.baemin_clone_android.src.shopping_cart.models.ShoppingCartShopResponse
import retrofit2.Call
import retrofit2.http.*

interface ShoppingCartRetrofitInterface {
    @GET("/store-title/{store-idx}")
    fun shoppingCartTitle(
        @Path("store-idx") idx: Int
    ): Call<ShoppingCartShopResponse>


    @POST("/basket/menu-info")
    fun shoppingCartItem(

        @Body body: ShoppingCart

    ):Call<ShoppingCartItemResponse>

    @POST("/order")
    fun order(
        @Body body: Order
    ): Call<OrderResponse>
}