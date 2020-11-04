package com.professionalandroid.apps.baemin_clone_android.src.map

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.src.map.models.UserLocation
import kotlinx.android.synthetic.main.layout_mapselect_item.view.*

class MapSelectRecyclerViewAdapter(private val maplist:MutableList<UserLocation>):
    RecyclerView.Adapter<MapSelectRecyclerViewAdapter.ViewHolder>(){

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        var old_address: TextView? = null
        var new_address: TextView? = null
        var idx: Int? = null
        init {
            old_address = view.old_address
            new_address = view.new_address
            idx = null
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflateView = LayoutInflater.from(parent.context).inflate(R.layout.layout_mapselect_item, parent,false)
        return ViewHolder(inflateView)
    }

    override fun getItemCount(): Int {
        return maplist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.old_address?.text = maplist[position].address
        holder.new_address?.text = maplist[position].roadAddress
        holder.idx = maplist[position].idx
    }
}