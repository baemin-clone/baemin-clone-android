package com.professionalandroid.apps.baemin_clone_android.src.history

import android.util.Log
import com.professionalandroid.apps.baemin_clone_android.src.ApplicationClass
import com.professionalandroid.apps.baemin_clone_android.src.history.interfaces.HistoryFragmentView
import com.professionalandroid.apps.baemin_clone_android.src.history.interfaces.HistoryRetrofitInterface
import com.professionalandroid.apps.baemin_clone_android.src.history.models.HistoryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryService(val mHistoryFragmentView: HistoryFragmentView) {
    val mHistoryRetrofitInterface: HistoryRetrofitInterface =
        ApplicationClass.retrofitService()!!.create(HistoryRetrofitInterface::class.java)

    fun getHistory(){
        mHistoryRetrofitInterface.getHistory(1, 10).enqueue(object : Callback<HistoryResponse>{
            override fun onFailure(call: Call<HistoryResponse>, t: Throwable) {
                Log.d("test", "history 실패")
            }

            override fun onResponse(
                call: Call<HistoryResponse>,
                response: Response<HistoryResponse>
            ) {
                val body = response.body()
                Log.d("test", body.toString())
                if(body?.code == 200) {
                    mHistoryFragmentView.getHistory(body)
                }
                else{
                    mHistoryFragmentView.HistoryIsEmpty()
                }
            }

        })
    }
}