package com.professionalandroid.apps.baemin_clone_android.src.map.navermap

import android.util.Log
import com.professionalandroid.apps.baemin_clone_android.src.ApplicationClass
import com.professionalandroid.apps.baemin_clone_android.src.map.navermap.interfaces.NaverMapFragmentView
import com.professionalandroid.apps.baemin_clone_android.src.map.navermap.interfaces.NaverMapRetrofitInterface
import com.professionalandroid.apps.baemin_clone_android.src.map.navermap.models.NaverMapsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NavermapServiece(private val mNaverMapFragmentView: NaverMapFragmentView) {

    val mNaverMapRetrofitInterface: NaverMapRetrofitInterface =
        ApplicationClass.NaverRetrofitService()!!.create(NaverMapRetrofitInterface::class.java)

    // Reverse Geocoding
    fun reverse_geocoding(header1: String, header2: String, coords: String, output: String, orders: String){
        mNaverMapRetrofitInterface.reverse_geocoding(header1, header2, coords, output, orders).enqueue(object : Callback<NaverMapsResponse>{
            override fun onFailure(call: Call<NaverMapsResponse>, t: Throwable) {
                Log.d("test", "실패")
            }

            override fun onResponse(
                call: Call<NaverMapsResponse>,
                response: Response<NaverMapsResponse>
            ) {
                val body = response.body()
                var addr0 = ""
                var addr1 = ""
                when (body?.results?.size) {
                    2 -> {
                        val addr = body.results[0].region
                        val roadaddr = body.results[1].region
                        addr0 = "${addr.area2.name} ${addr.area3.name} ${addr.area4.name} ${body.results[0].land.number1}" + if(body.results[0].land.number2.isNotEmpty()){"-" + body.results[0].land.number2} else{""}
                        addr1 = "${roadaddr.area2.name} ${roadaddr.area3.name} ${roadaddr.area4.name} ${body.results[1].land.name} ${body.results[1].land.number1}" + if(body.results[1].land.number2.isNotEmpty()){"-" + body.results[1].land.number2} else{""}
                    }
                    1 -> {
                        val addr = body.results[0].region
                        addr0 = "${addr.area2.name} ${addr.area3.name} ${addr.area4.name} ${body.results[0].land.number1}" + if(body.results[0].land.number2.isNotEmpty()){"-" + body.results[0].land.number2} else{""}
                        addr1 = ""
                    }
                    else -> {
                        addr0 = ""
                        addr1 = ""
                    }
                }
                Log.d("test1", "$addr0 + $addr1")
                mNaverMapFragmentView.saveNowPlace(addr0, addr1)
            }

        })
    }
}