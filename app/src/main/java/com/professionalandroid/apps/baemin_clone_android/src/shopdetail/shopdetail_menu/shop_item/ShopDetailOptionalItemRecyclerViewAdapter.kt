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

class ShopDetailOptionalItemRecyclerViewAdapter(): RecyclerView.Adapter<ShopDetailOptionalItemRecyclerViewAdapter.ViewHolder>(), ShopDetailOptionalItemSelectorRecyclerViewAdapter.ItemChecked {

    lateinit var optionalItem: MutableList<Option>
    lateinit var mcontext: Context
    lateinit var mlistener: ShopDetailOptionalItemRecyclerViewAdapter.ItemClicked
    lateinit var mRecyclerView: RecyclerView

    interface ItemClicked{
        fun click(view: View, price: Int, check:Boolean)
    }

    constructor(optionalItem: MutableList<Option>, context: Context, listener: ShopDetailOptionalItemRecyclerViewAdapter.ItemClicked):this(){
        this.optionalItem = optionalItem
        mcontext = context
        mlistener = listener
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var optionalRecyclerview: RecyclerView? = null
        var optionalItemTitle:TextView? = null
        init {
            optionalRecyclerview = view.category_recyclerview
            optionalItemTitle = view.category_item_title

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflateview = LayoutInflater.from(parent.context).inflate(R.layout.layout_shop_detail_item_category, parent, false)
        return ViewHolder(inflateview)
    }

    override fun getItemCount(): Int {
        return optionalItem.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val madapter = ShopDetailOptionalItemSelectorRecyclerViewAdapter(optionalItem[position].contents, mcontext, optionalItem[position].optionGroupIdx!!, this)
        holder.optionalItemTitle?.text = optionalItem[position].groupTitle
        holder.optionalRecyclerview?.layoutManager = LinearLayoutManager(mcontext)
        holder.optionalRecyclerview?.adapter = madapter
        holder.optionalRecyclerview?.setHasFixedSize(true)
        mRecyclerView = holder.optionalRecyclerview!!
        madapter.notifyDataSetChanged()
    }

    override fun check(view: View, position: Int) {
        val viewHolder =  mRecyclerView.findViewHolderForAdapterPosition(position) as ShopDetailOptionalItemSelectorRecyclerViewAdapter.SubViewHolder
        mlistener.click(view, Integer.parseInt(viewHolder.optionalItemPrice?.text.toString()), viewHolder.optionalCheckbox?.isChecked!!)
    }
}