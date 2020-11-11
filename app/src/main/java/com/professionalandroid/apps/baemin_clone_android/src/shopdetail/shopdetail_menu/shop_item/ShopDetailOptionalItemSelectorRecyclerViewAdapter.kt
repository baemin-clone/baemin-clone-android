package com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_menu.shop_item

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.professionalandroid.apps.baemin_clone_android.OptionArray
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_menu.shop_item.ShopDetailItemFragment.Companion.tempShoppingList
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_menu.shop_item.models.Content
import kotlinx.android.synthetic.main.layout_shop_detail_optional_item_selector.view.*

class ShopDetailOptionalItemSelectorRecyclerViewAdapter(): RecyclerView.Adapter<ShopDetailOptionalItemSelectorRecyclerViewAdapter.SubViewHolder>() {

    lateinit var optionalItem : List<Content?>
    lateinit var mcontext: Context
    var groupIdx: Int? = null

    constructor(optionalItem:List<Content?>, context:Context, groupidx: Int):this(){
        this.optionalItem = optionalItem
        mcontext = context
        groupIdx = groupidx
    }

    inner class SubViewHolder(view: View): RecyclerView.ViewHolder(view){
        val parentview = view
        var optionalCheckbox: CheckBox? = null
        var optionalItemTitle: TextView? = null
        var optionalItemPrice: TextView? = null
        var optionalItemIdx: Int? = null
        init {
            optionalCheckbox = view.optional_item_check
            optionalItemTitle = view.optional_item_title
            optionalItemPrice = view.optional_item_price

            // add and delete option
            parentview.setOnClickListener {
                optionalCheckbox?.isChecked = !optionalCheckbox?.isChecked!!
                if(optionalCheckbox?.isChecked!!){
                    var k = false
                    for(i in tempShoppingList){
                        if(i.optionGroupIdx == groupIdx){
                            i.options.add(optionalItemIdx!!)
                            k = true
                            break
                        }
                    }
                    if(!k){
                        tempShoppingList.add(OptionArray(groupIdx!!, mutableListOf(optionalItemIdx!!)))
                    }

                }
                else{
                    for(i in tempShoppingList){
                        if(i.optionGroupIdx == groupIdx){
                            i.options.remove(optionalItemIdx!!)
                        }
                    }
                }
            }
            optionalCheckbox?.setOnClickListener {
                if(optionalCheckbox?.isChecked!!){
                    var k = false
                    for(i in tempShoppingList){
                        if(i.optionGroupIdx == groupIdx){
                            i.options.add(optionalItemIdx!!)
                            k = true
                            break
                        }
                    }
                    if(!k){
                        tempShoppingList.add(OptionArray(groupIdx!!, mutableListOf(optionalItemIdx!!)))
                    }
                }
                else{
                    for(i in tempShoppingList){
                        if(i.optionGroupIdx == groupIdx){
                            i.options.remove(optionalItemIdx!!)
                        }
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubViewHolder {
        val inflateview = LayoutInflater.from(parent.context).inflate(R.layout.layout_shop_detail_optional_item_selector, parent, false)
        return SubViewHolder(inflateview)
    }

    override fun getItemCount(): Int {
        return optionalItem.size
    }

    override fun onBindViewHolder(holder: SubViewHolder, position: Int) {
        holder.optionalItemTitle?.text = optionalItem[position]?.title
        holder.optionalItemPrice?.text = optionalItem[position]?.price.toString()
        holder.optionalItemIdx = optionalItem[position]?.optionIdx
    }
}