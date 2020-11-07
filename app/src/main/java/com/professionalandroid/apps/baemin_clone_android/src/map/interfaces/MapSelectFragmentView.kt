package com.professionalandroid.apps.baemin_clone_android.src.map.interfaces

import com.professionalandroid.apps.baemin_clone_android.src.map.models.UserLocation

interface MapSelectFragmentView {
    fun addMaptoList(maplist: List<UserLocation>)
}