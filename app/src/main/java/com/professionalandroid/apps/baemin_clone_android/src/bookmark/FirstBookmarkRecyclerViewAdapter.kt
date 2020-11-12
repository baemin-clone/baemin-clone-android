package com.professionalandroid.apps.baemin_clone_android.src.bookmark

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.makeramen.roundedimageview.RoundedImageView
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.src.bookmark.models.Result
import kotlinx.android.synthetic.main.layout_shoplist_item.view.*

class FirstBookmarkRecyclerViewAdapter():RecyclerView.Adapter<FirstBookmarkRecyclerViewAdapter.ViewHolder>() {

    interface ShopClicked{
        fun movetoShop(view: View, position: Int)
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        var parentview = view
        var storeIdx: Int? = null
        var bookmark_img: RoundedImageView? = null
        var bookmark_title: TextView? = null
        var bookmark_star: TextView? = null
        var bookmark_receiver_num: TextView? = null
        var bookmark_recommendation: TextView? = null
        var bookmark_delivery_time: TextView? = null
        var bookmark_min_order: TextView? = null
        var bookmark_tip: TextView? = null

        init {
            bookmark_img = view.shop_img
            bookmark_title = view.shop_title
            bookmark_star = view.shop_star
            bookmark_receiver_num = view.shop_receiver_num
            bookmark_recommendation = view.shop_recommendation
            bookmark_delivery_time = view.shop_delivery_time
            bookmark_min_order = view.shop_min_order
            bookmark_tip = view.shop_tip
            storeIdx = 0
            parentview.setOnClickListener {
                mlistener.movetoShop(view, adapterPosition)
            }
        }
    }

    lateinit var bookmarkList: MutableList<Result>
    lateinit var mcontext: Context
    lateinit var mlistener: ShopClicked

    constructor(bookmarkList: MutableList<Result>, context: Context, listener: ShopClicked): this(){
        this.bookmarkList = bookmarkList
        mcontext = context
        mlistener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflateview = LayoutInflater.from(parent.context).inflate(R.layout.layout_shoplist_item, parent, false)
        return ViewHolder(inflateview)
    }

    override fun getItemCount(): Int {
        return bookmarkList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(mcontext)
            .load(bookmarkList[position].logo)
            .centerCrop()
            .into(holder.bookmark_img!!)
        holder.bookmark_title?.text = bookmarkList[position].title
        holder.bookmark_star?.text = bookmarkList[position].avgStar
        holder.bookmark_receiver_num?.text = bookmarkList[position].reviewNum
        holder.bookmark_recommendation?.text = bookmarkList[position].recommendation
        holder.bookmark_delivery_time?.text = bookmarkList[position].deliveryTime
        holder.bookmark_min_order?.text = bookmarkList[position].minOrderAmount
        holder.bookmark_tip?.text = bookmarkList[position].tip
        holder.storeIdx = bookmarkList[position].storeIdx
    }
}