package com.professionalandroid.apps.baemin_clone_android.src.shopping_cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.professionalandroid.apps.baemin_clone_android.R
import kotlinx.android.synthetic.main.layout_shoppingcart_option.view.*

class ShoppingCartOptionRecyclerViewAdapter(val optionList: MutableList<String>): RecyclerView.Adapter<ShoppingCartOptionRecyclerViewAdapter.OptionViewHolder>() {

    inner class OptionViewHolder(view: View): RecyclerView.ViewHolder(view){
        var option: TextView? = null
        init {
            option = view.shoppingCart_option
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        val inflaterview = LayoutInflater.from(parent.context).inflate(R.layout.layout_shoppingcart_option, parent, false)
        return OptionViewHolder(inflaterview)
    }

    override fun getItemCount(): Int {
        return optionList.size
    }

    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
        holder.option?.text = optionList[position]
    }
}