package com.professionalandroid.apps.baemin_clone_android.src.myinfoFragment.modifyMyInfo.interfaces

import com.professionalandroid.apps.baemin_clone_android.src.myinfoFragment.modifyMyInfo.models.ProfilePictureResponse

interface ModifyMyinfoFragmentView
{
    fun deletejwt()
    fun putUserdata(nickname:String?, profileImg:String, email: String?, phone: String?)
}