package com.professionalandroid.apps.baemin_clone_android.src.map

import android.util.Log
import com.professionalandroid.apps.baemin_clone_android.src.ApplicationClass
import com.professionalandroid.apps.baemin_clone_android.src.map.interfaces.MapSelectFragmentView
import com.professionalandroid.apps.baemin_clone_android.src.map.interfaces.MapSelectRetrofitInterface
import com.professionalandroid.apps.baemin_clone_android.src.map.models.DeleteMapResponse
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
                Log.d("test", "maplist 불러오기 실패")
            }

            override fun onResponse(call: Call<MapResponse>, response: Response<MapResponse>) {
                Log.d("test", response.body().toString())
                val body = response.body()

                if(body?.result != null && body.result.userLocationRows.isNotEmpty()){
                    mMapSelectFragmentView.addMaptoList(response.body()?.result?.userLocationRows!!)
                }

            }

        })
    }

    fun deleteMap(idx: Int){
        mMapSelectRetrofitInterface.deleteMap(idx).enqueue(object : Callback<DeleteMapResponse>{
            override fun onFailure(call: Call<DeleteMapResponse>, t: Throwable) {
                Log.d("test", "delete map 실패")
            }

            override fun onResponse(
                call: Call<DeleteMapResponse>,
                response: Response<DeleteMapResponse>
            ) {
                val body = response.body()
                Log.d("test", body.toString())
            }

        })
    }
}