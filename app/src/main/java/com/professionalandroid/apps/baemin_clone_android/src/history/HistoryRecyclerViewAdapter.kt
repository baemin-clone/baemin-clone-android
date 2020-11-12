package com.professionalandroid.apps.baemin_clone_android.src.history

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.src.history.models.Result
import kotlinx.android.synthetic.main.layout_history_item.view.*

class HistoryRecyclerViewAdapter(): RecyclerView.Adapter<HistoryRecyclerViewAdapter.ViewHolder>() {

    interface ShopChecked{
        fun movetoShop(view: View, position: Int)
    }

    lateinit var historyList: MutableList<Result>
    lateinit var mcontext: Context
    lateinit var mlistener: ShopChecked

    constructor(historyList: MutableList<Result>, context: Context, listener: ShopChecked): this(){
        this.historyList = historyList
        mcontext = context
        mlistener = listener
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        var historyIdx: Int = 0
        var history_img: ImageView? = null
        var history_date: TextView? = null
        var history_title: TextView? = null
        var history_description: TextView? = null
        var history_review: ImageView? = null
        var history_able: TextView? = null
        var history_shop: ImageView? = null
        init {
            history_img = view.history_img
            history_date = view.history_date
            history_title = view.history_title
            history_description = view.history_description
            history_review = view.history_review
            history_able = view.history_able
            history_shop = view.history_shop.apply {
                setOnClickListener {
                    mlistener.movetoShop(view, adapterPosition)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflateview = LayoutInflater.from(parent.context).inflate(R.layout.layout_history_item, parent, false)
        return ViewHolder(inflateview)
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.historyIdx = historyList[position].storeIdx
        Glide.with(mcontext)
            .load(historyList[position].logo)
            .centerCrop()
            .into(holder.history_img!!)
        holder.history_date?.text = historyList[position].createdAt
        holder.history_title?.text = historyList[position].title
        holder.history_description?.text = historyList[position].orderHistory
        if(historyList[position].isWrite){
            holder.history_review?.setImageResource(R.drawable.write_review)
            holder.history_able?.text = "리뷰 쓰기는 주문 이후 3일 동안만 가능합니다."
        }
    }
}