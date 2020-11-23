package com.professionalandroid.apps.baemin_clone_android.src.history

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
import com.professionalandroid.apps.baemin_clone_android.src.shoplist.ShoplistActivity
import com.professionalandroid.apps.baemin_clone_android.src.history.interfaces.HistoryFragmentView
import com.professionalandroid.apps.baemin_clone_android.src.history.models.HistoryResponse
import com.professionalandroid.apps.baemin_clone_android.src.history.models.Result
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.android.synthetic.main.fragment_history.view.*

class HistoryFragment : Fragment(), HistoryFragmentView, HistoryRecyclerViewAdapter.ShopChecked {

    val mHistoryService = HistoryService(this)
    val historyList = mutableListOf<Result>()
    lateinit var mHistoryRecyclerView: RecyclerView
    lateinit var mHistoryRecyclerViewAdapter: HistoryRecyclerViewAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mHistoryRecyclerViewAdapter = HistoryRecyclerViewAdapter(historyList, context, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_history, container, false)

        historyList.clear()

        mHistoryRecyclerView = view.history_recyclerview
        mHistoryRecyclerView.layoutManager = LinearLayoutManager(context)
        mHistoryRecyclerView.adapter = mHistoryRecyclerViewAdapter

        mHistoryService.getHistory()

        return view
    }

    override fun getHistory(body: HistoryResponse) {
        historyList.addAll(body.result)
        mHistoryRecyclerViewAdapter.notifyDataSetChanged()

    }

    override fun HistoryIsEmpty() {
        empty_history.visibility = View.VISIBLE
    }

    override fun movetoShop(view: View, position: Int) {
        val viewHolder = mHistoryRecyclerView.findViewHolderForAdapterPosition(position) as HistoryRecyclerViewAdapter.ViewHolder
        val intent = Intent(context, ShoplistActivity::class.java)
        intent.putExtra("shopidx", viewHolder.historyIdx)
        startActivity(intent)
        (activity as MainActivity).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

}