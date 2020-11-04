package com.professionalandroid.apps.baemin_clone_android.src.map

import com.professionalandroid.apps.baemin_clone_android.src.ApplicationClass
import com.professionalandroid.apps.baemin_clone_android.src.map.interfaces.MapSelectFragmentView
import com.professionalandroid.apps.baemin_clone_android.src.map.interfaces.MapSelectRetrofitInterface

class MapSelectService(private val mMapSelectFragmentView: MapSelectFragmentView) {
    val mMapSelectRetrofitInterface: MapSelectRetrofitInterface =
        ApplicationClass.retrofitService()!!.create(MapSelectRetrofitInterface::class.java)

}