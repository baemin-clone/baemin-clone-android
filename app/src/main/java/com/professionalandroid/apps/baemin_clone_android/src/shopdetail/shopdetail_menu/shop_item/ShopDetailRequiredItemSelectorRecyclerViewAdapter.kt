package com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_menu.shop_item

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_menu.shop_item.ShopDetailItemFragment.Companion.tempShoppingList
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_menu.shop_item.models.Content
import kotlinx.android.synthetic.main.layout_shop_detail_required_item_selector.view.*


class ShopDetailRequiredItemSelectorRecyclerViewAdapter(items: List<Content>, context: Context) :
    RecyclerView.Adapter<ShopDetailRequiredItemSelectorRecyclerViewAdapter.ViewHolder>() {
    var mSelectedItem = -1
    var mItems: List<Content>
    private val mContext: Context
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mText.text = mItems[position].title
        holder.price.text = mItems[position].price.toString()
        holder.mRadio.isChecked = position == mSelectedItem
        holder.requiredItemIdx = mItems[position].optionIdx
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(mContext)
        val view: View = inflater.inflate(R.layout.layout_shop_detail_required_item_selector, parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var mRadio: RadioButton
        var mText: TextView
        var price: TextView
        var requiredItemIdx: Int

        init {
            mText = view.required_item_title
            mRadio = view.required_item_radio
            price = view.required_item_price
            requiredItemIdx = 0
            val clickListener = View.OnClickListener {
                tempShoppingList.remove(mSelectedItem)
                mSelectedItem = adapterPosition
                tempShoppingList.add(mSelectedItem)
                notifyDataSetChanged()
            }
            itemView.setOnClickListener(clickListener)
            mRadio.setOnClickListener(clickListener)
        }
    }

    init {
        mContext = context
        mItems = items
    }
}