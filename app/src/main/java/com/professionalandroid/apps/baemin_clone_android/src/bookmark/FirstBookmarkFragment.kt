package com.professionalandroid.apps.baemin_clone_android.src.bookmark

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.src.Shoplist.ShoplistActivity
import com.professionalandroid.apps.baemin_clone_android.src.bookmark.interfaces.FirstBookmarkFragmentView
import com.professionalandroid.apps.baemin_clone_android.src.bookmark.models.BookmarkPageResponse
import com.professionalandroid.apps.baemin_clone_android.src.bookmark.models.Result
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.ShopDetailFragment
import kotlinx.android.synthetic.main.fragment_first_bookmark.*
import kotlinx.android.synthetic.main.fragment_first_bookmark.view.*

class FirstBookmarkFragment : Fragment(), FirstBookmarkFragmentView, FirstBookmarkRecyclerViewAdapter.ShopClicked {

    lateinit var mFirstBookmarkRecyclerView: RecyclerView
    lateinit var mFirstBookmarkRecyclerViewAdapter: FirstBookmarkRecyclerViewAdapter
    var bookmarkList = mutableListOf<Result>()

    val mFirstBookmarkService = FIrstBookmarkService(this)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mFirstBookmarkRecyclerViewAdapter = FirstBookmarkRecyclerViewAdapter(bookmarkList, context, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first_bookmark, container, false)

        bookmarkList.clear()
        mFirstBookmarkRecyclerView = view.bookmark_recyclerview
        mFirstBookmarkRecyclerView.layoutManager = LinearLayoutManager(context)
        mFirstBookmarkRecyclerView.adapter = mFirstBookmarkRecyclerViewAdapter

        mFirstBookmarkService.getBookmark()

        return view
    }

    override fun getBookmark(body: BookmarkPageResponse) {
        bookmark_empty_img.visibility = View.GONE
        bookmarkList.addAll(body.result)
        mFirstBookmarkRecyclerViewAdapter.notifyDataSetChanged()
    }

    override fun bookmarkIsEmpty() {
        bookmark_empty_img.visibility = View.VISIBLE
    }

    override fun movetoShop(view: View, position: Int) {
        val viewHolder = mFirstBookmarkRecyclerView.findViewHolderForAdapterPosition(position) as FirstBookmarkRecyclerViewAdapter.ViewHolder

        val intent = Intent(context, ShoplistActivity::class.java)
        intent.putExtra("shopidx", viewHolder.storeIdx)
        startActivity(intent)
    }


}