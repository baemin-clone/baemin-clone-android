package com.professionalandroid.apps.baemin_clone_android.src.home.delivery

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.makeramen.roundedimageview.RoundedImageView
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.src.home.delivery.models.Result1
import kotlinx.android.synthetic.main.layout_recommaedation1.view.*

class DeliveryRecommendation1RecyclerViewAdapter(): RecyclerView.Adapter<DeliveryRecommendation1RecyclerViewAdapter.ViewHolder>() {

    lateinit var recommendation1List: MutableList<Result1>
    lateinit var mcontext: Context

    constructor(recommendation1List: MutableList<Result1>, context: Context): this(){
        this.recommendation1List = recommendation1List
        mcontext = context
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var recommendation1_logo: RoundedImageView? = null
        var recommendation1_title: TextView? = null
        var recommendation1_description: TextView? = null
        var recommendation_time: TextView? = null

        init {
            recommendation1_logo = view.recommendation1_logo
            recommendation1_title = view.recommendation1_title
            recommendation1_description = view.recommendation1_description
            recommendation_time = view.recommendation1_time
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflateview = LayoutInflater.from(parent.context).inflate(R.layout.layout_recommaedation1, parent, false)
        return ViewHolder(inflateview)
    }

    override fun getItemCount(): Int {
        return recommendation1List.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.recommendation1_title?.text = recommendation1List[position].title
        holder.recommendation1_description?.text = recommendation1List[position].mainMenu
        holder.recommendation_time?.text = recommendation1List[position].deliveryTime
        Glide.with(mcontext)
            .load(recommendation1List[position].logo)
            .centerCrop()
            .into(holder.recommendation1_logo!!)
    }
}