package com.professionalandroid.apps.baemin_clone_android.src.login.interfaces

interface LoginActivityView {
    fun successLogin()
    fun isAlreadyRegistered(code: Int, token: String, jwt: String?)
    fun saveJwt(jwt: String)
}