package com.professionalandroid.apps.baemin_clone_android.src.shopping_cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.professionalandroid.apps.baemin_clone_android.R

class ShoppingCartRecyclerViewAdapter():RecyclerView.Adapter<ShoppingCartRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflateview = LayoutInflater.from(parent.context).inflate(R.layout.layout_shoppingcart_item, parent, false)
        return ViewHolder(inflateview)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}