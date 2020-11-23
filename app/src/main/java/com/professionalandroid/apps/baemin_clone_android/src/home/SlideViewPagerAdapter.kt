package com.professionalandroid.apps.baemin_clone_android.src.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.professionalandroid.apps.baemin_clone_android.R
import kotlinx.android.synthetic.main.item_home_viewpager.view.*

class SlideViewPagerAdapter(var items: MutableList<Int>): RecyclerView.Adapter<SlideViewPagerAdapter.PagerViewHolder>() {

    class PagerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var img: ImageView? = null
        init {
            img = view.home_delivery_viewpager_img
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val infateview =  LayoutInflater.from(parent.context).inflate(R.layout.item_home_viewpager, parent, false)
        return PagerViewHolder(infateview)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.img?.setImageResource(items[position])
    }
}

