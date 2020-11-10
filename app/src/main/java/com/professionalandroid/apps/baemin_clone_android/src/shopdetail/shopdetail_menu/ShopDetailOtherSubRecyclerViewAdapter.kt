package com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_menu

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_menu.models.Content
import kotlinx.android.synthetic.main.layout_shop_detail_other_sub_item.view.*

class ShopDetailOtherSubRecyclerViewAdapter(): RecyclerView.Adapter<ShopDetailOtherSubRecyclerViewAdapter.SubViewHolder>() {

    interface SubItemSelectedInterface{
        fun onSubItemSelected(view: View, position: Int)
    }


    var otherSubMenu: List<Content>? = null
    lateinit var mcontext: Context
    lateinit var mlistener: SubItemSelectedInterface

    constructor(otherSubMenu: List<Content>?, mcontext: Context, listener: SubItemSelectedInterface):this(){
        this.otherSubMenu = otherSubMenu
        this.mcontext = mcontext
        mlistener = listener
    }

    inner class SubViewHolder(view: View): RecyclerView.ViewHolder(view){
        var parentview = view
        var other_title: TextView? = null
        var other_description: TextView? = null
        var other_price: TextView? = null
        var idx: Int? = null
        init {
            other_title = view.other_sub_title
            other_description = view.other_sub_description
            other_price = view.other_sub_price
            idx = 0
            parentview.setOnClickListener {
                mlistener.onSubItemSelected(view, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SubViewHolder {
        val inflateview = LayoutInflater.from(parent.context).inflate(R.layout.layout_shop_detail_other_sub_item, parent, false)
        return SubViewHolder(inflateview)
    }

    override fun getItemCount(): Int {
        return otherSubMenu?.size ?: 0
    }

    override fun onBindViewHolder(
        holder: SubViewHolder,
        position: Int
    ) {
        if(otherSubMenu != null) {
            holder.other_title?.text = otherSubMenu!![position].menuTitle
            holder.other_description?.text = otherSubMenu!![position].menuDescription
            holder.other_price?.text = otherSubMenu!![position].price
            holder.idx = otherSubMenu!![position].menuIdx
        }
    }

}