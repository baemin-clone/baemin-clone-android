package com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_menu

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.makeramen.roundedimageview.RoundedImageView
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_menu.models.Content
import kotlinx.android.synthetic.main.layout_shop_detail_menu_signature_item.view.*

class ShopDetailSignatureRecyclerViewAdapter(): RecyclerView.Adapter<ShopDetailSignatureRecyclerViewAdapter.ViewHolder>() {

    interface ItemSelectedInterface {
        fun onItemSelected(v: View, position: Int)
    }

    lateinit var signatureMenu: List<Content?>
    lateinit var mcontext: Context
    lateinit var mlistener: ItemSelectedInterface

    constructor(signatureMenu: List<Content?>, mcontext: Context, listener: ItemSelectedInterface): this(){
        this.signatureMenu = signatureMenu
        this.mcontext = mcontext
        mlistener = listener
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        var parentview = view
        var signature_title: TextView? = null
        var signature_description: TextView? = null
        var signature_price: TextView? = null
        var signature_img: RoundedImageView? = null
        var idx: Int? = null

        init {
            signature_title = view.signature_title
            signature_description = view.signature_description
            signature_price = view.signature_price
            signature_img = view.signature_img
            idx = 0
            parentview.setOnClickListener {
                mlistener.onItemSelected(view, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflaterView = LayoutInflater.from(parent.context).inflate(R.layout.layout_shop_detail_menu_signature_item,parent,false)
        return ViewHolder(inflaterView)
    }

    override fun getItemCount(): Int {
        return signatureMenu.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.signature_title?.text = signatureMenu[position]?.menuTitle
        holder.signature_description?.text = signatureMenu[position]?.menuDescription
        holder.signature_price?.text = signatureMenu[position]?.price
        holder.idx = signatureMenu[position]?.menuIdx
        Glide.with(mcontext)
            .load(signatureMenu[position]?.photoPath)
            .centerCrop()
            .into(holder.signature_img!!)
    }
}