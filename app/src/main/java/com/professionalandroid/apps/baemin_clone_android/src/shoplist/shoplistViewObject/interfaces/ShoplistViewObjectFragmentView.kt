package com.professionalandroid.apps.baemin_clone_android.src.shoplist.shoplistViewObject.interfaces

import com.professionalandroid.apps.baemin_clone_android.src.shoplist.shoplistViewObject.models.Result

interface ShoplistViewObjectFragmentView {
    fun firstload()
    fun loadmore(templist: List<Result>?)

}