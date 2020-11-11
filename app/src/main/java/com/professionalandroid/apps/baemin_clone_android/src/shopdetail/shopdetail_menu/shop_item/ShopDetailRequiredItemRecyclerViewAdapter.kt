package com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_menu.shop_item

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_menu.shop_item.models.Option
import kotlinx.android.synthetic.main.layout_shop_detail_item_category.view.*

class ShopDetailRequiredItemRecyclerViewAdapter(): RecyclerView.Adapter<ShopDetailRequiredItemRecyclerViewAdapter.ViewHolder>() {

    lateinit var requiredItem: MutableList<Option>
    lateinit var mcontext: Context

    constructor(requiredItem: MutableList<Option>, context: Context):this(){
        this.requiredItem = requiredItem
        mcontext = context
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        var selectorRecyclerview: RecyclerView? = null
        var requiredItemTitle: TextView? = null

        init {
            selectorRecyclerview = view.category_recyclerview
            requiredItemTitle = view.category_item_title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflateview = LayoutInflater.from(parent.context).inflate(R.layout.layout_shop_detail_item_category, parent, false)
        return ViewHolder(inflateview)
    }

    override fun getItemCount(): Int {
        return requiredItem.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val madapter =
            ShopDetailRequiredItemSelectorRecyclerViewAdapter(
                requiredItem[position].contents,
                mcontext,
                requiredItem[position].optionGroupIdx!!
            )
        holder.requiredItemTitle?.text = requiredItem[position].groupTitle
        holder.selectorRecyclerview?.layoutManager = LinearLayoutManager(mcontext)
        holder.selectorRecyclerview?.adapter = madapter
        holder.selectorRecyclerview?.setHasFixedSize(true)
        madapter.notifyDataSetChanged()
    }
}