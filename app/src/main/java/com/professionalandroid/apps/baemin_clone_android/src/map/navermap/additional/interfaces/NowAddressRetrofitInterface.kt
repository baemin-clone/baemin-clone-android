package com.professionalandroid.apps.baemin_clone_android.src.map.navermap.additional.interfaces

import com.professionalandroid.apps.baemin_clone_android.NowAddress
import com.professionalandroid.apps.baemin_clone_android.src.map.navermap.additional.models.NowAddressResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface NowAddressRetrofitInterface {
    @POST("/my-address")
    fun saveNewAddress(
        @Body nowAddress: NowAddress
    ): Call<NowAddressResponse>
}