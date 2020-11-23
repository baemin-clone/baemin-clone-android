package com.professionalandroid.apps.baemin_clone_android.src.home.delivery

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.src.home.delivery.models.Result
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.layout_brand_custom.view.*
import java.util.*

class DeliveryRecyclerViewAdapter(val brandList: MutableList<Result>, val mcontext: Context): RecyclerView.Adapter<DeliveryRecyclerViewAdapter.ViewHolder>() {
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        var brandLogoImg: CircleImageView? = null
        var brandTitle: TextView? = null
        var brandDescription: TextView? = null
        var brandCoupon: LinearLayout? = null
        var brandNewMenu: LinearLayout? = null
        var brandEvent: TextView? = null
        var bacground: LinearLayout? = null

        init {
            brandLogoImg = view.brand_logo
            brandTitle = view.brand_title
            brandDescription = view.brand_description
            brandCoupon = view.brand_coupon
            brandNewMenu = view.brand_newmenu
            brandEvent = view.brand_event
            bacground = view.brand_background
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflateview = LayoutInflater.from(parent.context).inflate(R.layout.layout_brand_custom, parent, false)
        return ViewHolder(inflateview)
    }

    override fun getItemCount(): Int {
        return brandList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.brandTitle?.text = brandList[position].title
        holder.brandDescription?.text = brandList[position].description
        holder.brandEvent?.text = brandList[position].notice
        Glide.with(mcontext)
            .load(brandList[position].logo)
            .centerCrop()
            .into(holder.brandLogoImg!!)

        val rnd = Random()
        val color: Int = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        holder.bacground?.setBackgroundColor(color)

        if(brandList[position].coupon == 1){
            holder.brandCoupon?.visibility = View.GONE
        }
        else{
            holder.brandCoupon?.apply {
                setBackgroundColor(color)
                alpha = 0.5F
            }
        }
        if(brandList[position].newMenu == 1){
            holder.brandNewMenu?.visibility = View.GONE
        }else{
            holder.brandNewMenu?.apply {
                setBackgroundColor(color)
                alpha = 0.5F
            }
        }


    }
}