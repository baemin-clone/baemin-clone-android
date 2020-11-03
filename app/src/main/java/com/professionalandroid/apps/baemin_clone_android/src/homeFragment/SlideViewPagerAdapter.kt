package com.professionalandroid.apps.baemin_clone_android.src.homeFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.professionalandroid.apps.baemin_clone_android.R
import kotlinx.android.synthetic.main.item_home_viewpager.view.*

class SlideViewPagerAdapter(var items: MutableList<String>): RecyclerView.Adapter<SlideViewPagerAdapter.PagerViewHolder>() {

    class PagerViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var textView:TextView? = null
        init {
            textView= v.temp
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
        holder.textView?.text = "RecyclerViewAdapter\nPage ${position+1}\n${items[position]}"
    }
}

