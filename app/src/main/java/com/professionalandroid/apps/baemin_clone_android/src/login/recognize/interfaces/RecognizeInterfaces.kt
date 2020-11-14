package com.professionalandroid.apps.baemin_clone_android.src.login.recognize.interfaces

import com.professionalandroid.apps.baemin_clone_android.PhoneNumber
import com.professionalandroid.apps.baemin_clone_android.PhoneRecognization
import com.professionalandroid.apps.baemin_clone_android.src.login.recognize.models.SMSResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RecognizeInterfaces {
    @POST("/authSMS")
    fun authSMS(
        @Body phoneNumber:PhoneNumber
    ):Call<SMSResponse>

    @POST("/authNumber")
    fun authNumber(
        @Body data: PhoneRecognization
    ):Call<SMSResponse>
}