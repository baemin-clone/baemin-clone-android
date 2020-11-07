package com.professionalandroid.apps.baemin_clone_android.src.Shoplist.ShoplistViewObject.interfaces

import com.professionalandroid.apps.baemin_clone_android.src.Shoplist.ShoplistViewObject.models.Result

interface ShoplistViewObjectFragmentView {
    fun firstload()
    fun loadmore(templist: List<Result>?)

}