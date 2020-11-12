package com.professionalandroid.apps.baemin_clone_android.src.shopping_cart.interfaces

import com.professionalandroid.apps.baemin_clone_android.src.shopping_cart.models.OrderResponse
import com.professionalandroid.apps.baemin_clone_android.src.shopping_cart.models.ShoppingCartItemResponse
import com.professionalandroid.apps.baemin_clone_android.src.shopping_cart.models.ShoppingCartShopResponse

interface ShoppingCartFragmentView {
    fun shoppingCartTitle(body:ShoppingCartShopResponse)
    fun shoppingCartItem(body: ShoppingCartItemResponse, num: Int)
}