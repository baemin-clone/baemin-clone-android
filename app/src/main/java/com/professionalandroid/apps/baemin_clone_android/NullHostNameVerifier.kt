package com.professionalandroid.apps.baemin_clone_android

import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSession

class NullHostNameVerifier: HostnameVerifier {
    override fun verify(p0: String?, p1: SSLSession?): Boolean {
        return true
    }
}