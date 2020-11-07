package com.professionalandroid.apps.baemin_clone_android.src.map

import android.util.Log
import com.professionalandroid.apps.baemin_clone_android.src.ApplicationClass
import com.professionalandroid.apps.baemin_clone_android.src.map.interfaces.MapSelectFragmentView
import com.professionalandroid.apps.baemin_clone_android.src.map.interfaces.MapSelectRetrofitInterface
import com.professionalandroid.apps.baemin_clone_android.src.map.models.MapResponse
import com.professionalandroid.apps.baemin_clone_android.src.map.models.UserLocation
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapSelectService(private val mMapSelectFragmentView: MapSelectFragmentView) {
    val mMapSelectRetrofitInterface: MapSelectRetrofitInterface =
        ApplicationClass.retrofitService()!!.create(MapSelectRetrofitInterface::class.java)

    fun getMapList(maplist: MutableList<UserLocation>){
        mMapSelectRetrofitInterface.getMapList(1,10).enqueue(object : Callback<MapResponse>{
            override fun onFailure(call: Call<MapResponse>, t: Throwable) {
                Log.d("test", "실패")
            }

            override fun onResponse(call: Call<MapResponse>, response: Response<MapResponse>) {
                Log.d("test", response.body().toString())
                if(response.body()?.result?.userLocationRows?.isNotEmpty()!!){
                    mMapSelectFragmentView.addMaptoList(response.body()?.result?.userLocationRows!!)
                }
            }

        })
    }
}