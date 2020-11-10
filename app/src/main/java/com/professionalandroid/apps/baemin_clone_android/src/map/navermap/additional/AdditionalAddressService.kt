package com.professionalandroid.apps.baemin_clone_android.src.map.navermap.additional

import android.util.Log
import com.professionalandroid.apps.baemin_clone_android.NowAddress
import com.professionalandroid.apps.baemin_clone_android.src.ApplicationClass
import com.professionalandroid.apps.baemin_clone_android.src.map.navermap.additional.interfaces.AdditionalAddressFragmentView
import com.professionalandroid.apps.baemin_clone_android.src.map.navermap.additional.interfaces.NowAddressRetrofitInterface
import com.professionalandroid.apps.baemin_clone_android.src.map.navermap.additional.models.NowAddressResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdditionalAddressService(val mAdditionalAddressFragmentView: AdditionalAddressFragmentView) {

    val mNowAddressRetrofitInterface: NowAddressRetrofitInterface =
        ApplicationClass.retrofitService()!!.create(NowAddressRetrofitInterface::class.java)

    fun saveNowAddress(data: NowAddress){
        mNowAddressRetrofitInterface.saveNewAddress(data).enqueue(object:
            Callback<NowAddressResponse> {
            override fun onFailure(call: Call<NowAddressResponse>, t: Throwable) {
                Log.d("test", "saveNowAddress 실패")
            }

            override fun onResponse(
                call: Call<NowAddressResponse>,
                response: Response<NowAddressResponse>
            ) {
                val body = response.body()
                Log.d("test", body.toString())
                mAdditionalAddressFragmentView.movetoMain()
            }

        })
    }
}