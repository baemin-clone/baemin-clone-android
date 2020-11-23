package com.professionalandroid.apps.baemin_clone_android.src.shoplist.shoplistViewObject

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.makeramen.roundedimageview.RoundedImageView
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.src.shoplist.shoplistViewObject.models.Result
import kotlinx.android.synthetic.main.layout_shoplist_item.view.*


class ShoplistViewObjectRecyclerViewAdapter(): RecyclerView.Adapter<ShoplistViewObjectRecyclerViewAdapter.ViewHolder>() {

    lateinit var mcontext:Context
    lateinit var shops:MutableList<Result>
    lateinit var onLoadMoreListener: OnLoadMoreListener
    lateinit var linearLayoutManager: LinearLayoutManager

    constructor(shoplist: MutableList<Result>, onLoadMoreListener: OnLoadMoreListener, context: Context):this(){
        this.onLoadMoreListener = onLoadMoreListener
        shops = shoplist
        mcontext = context
    }

    var isMoreLoading = false
    var visibleThreshold = 1
    var firstVisibleItem = 0
    var visibleItemCount = 0
    var totalItemCount = 0
    var lastVisibleItem = 0

    interface OnLoadMoreListener{
        fun onLoadMore()
        fun onItemSeledted(v:View, position: Int)
    }

    fun setRecyclerView(view:RecyclerView){
        view.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                visibleItemCount = recyclerView.getChildCount();
                totalItemCount = linearLayoutManager.getItemCount();
                firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                Log.d("total", totalItemCount.toString());
                Log.d("visible", visibleItemCount.toString());

                Log.d("first", firstVisibleItem.toString());
                Log.d("last", lastVisibleItem.toString());

                if (!isMoreLoading && (totalItemCount - visibleItemCount)<= (firstVisibleItem + visibleThreshold)) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                    isMoreLoading = true;
                }
            }
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflateview = LayoutInflater.from(parent.context).inflate(
            R.layout.layout_shoplist_item, parent
        , false)
        return ViewHolder(inflateview)
    }

    fun addItemMore(newOne:List<Result>){
        shops.addAll(newOne)
        notifyItemRangeChanged(0, shops.size)
    }

    override fun getItemCount(): Int {
        return shops.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(mcontext)
            .load(shops[position].logo)
            .centerCrop()
            .into(holder.shop_img!!)
        holder.shop_title?.text = shops[position].title
        holder.shop_star?.text = shops[position].star
        holder.shop_receiver_num?.text = shops[position].reviewNum
        holder.shop_recommendation?.text = shops[position].recommendation
        holder.shop_delivery_time?.text = shops[position].deliveryTIME
        holder.shop_min_order?.text = shops[position].minOrderAmount
        holder.shop_tip?.text = shops[position].tip
        holder.storeIdx = shops[position].storeIdx
    }

    inner class ViewHolder(view: View?): RecyclerView.ViewHolder(view!!){
        var parentview = view
        var storeIdx: Int? = null
        var shop_img: RoundedImageView? = null
        var shop_title: TextView? = null
        var shop_star: TextView? = null
        var shop_receiver_num: TextView? = null
        var shop_recommendation: TextView? = null
        var shop_delivery_time: TextView? = null
        var shop_min_order: TextView? = null
        var shop_tip: TextView? = null

        init {
            shop_img = view?.shop_img
            shop_title = view?.shop_title
            shop_star = view?.shop_star
            shop_receiver_num = view?.shop_receiver_num
            shop_recommendation = view?.shop_recommendation
            shop_delivery_time = view?.shop_delivery_time
            shop_min_order = view?.shop_min_order
            shop_tip = view?.shop_tip
            parentview?.setOnClickListener {
                onLoadMoreListener.onItemSeledted(view!!, adapterPosition)
            }
            storeIdx = 0
        }

    }



}