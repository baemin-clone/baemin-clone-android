package com.professionalandroid.apps.baemin_clone_android.src.bookmark

import android.util.Log
import com.professionalandroid.apps.baemin_clone_android.src.ApplicationClass
import com.professionalandroid.apps.baemin_clone_android.src.bookmark.interfaces.FirstBookmarkFragmentView
import com.professionalandroid.apps.baemin_clone_android.src.bookmark.interfaces.FirstBookmarkRetrofitInterface
import com.professionalandroid.apps.baemin_clone_android.src.bookmark.models.BookmarkPageResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FIrstBookmarkService(val mFirstBookmarkFragmentView: FirstBookmarkFragmentView) {
    val mFirstBookmarkRetrofitInterface: FirstBookmarkRetrofitInterface =
        ApplicationClass.retrofitService()!!.create(FirstBookmarkRetrofitInterface ::class.java)

    fun getBookmark(){
        mFirstBookmarkRetrofitInterface.getBookmark(1, 10).enqueue(object :
            Callback<BookmarkPageResponse> {
            override fun onFailure(call: Call<BookmarkPageResponse>, t: Throwable) {
                Log.d("test", "bookmarkPage 실패")
            }

            override fun onResponse(
                call: Call<BookmarkPageResponse>,
                response: Response<BookmarkPageResponse>
            ) {
                val body = response.body()
                Log.d("test", body.toString())
                if (body?.code == 200){
                    mFirstBookmarkFragmentView.getBookmark(body)
                }
                else{
                    mFirstBookmarkFragmentView.bookmarkIsEmpty()
                }
            }

        })
    }
}