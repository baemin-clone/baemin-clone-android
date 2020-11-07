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
        var address: TextView? = null
        var road_address: TextView? = null
        var road_address_guide: TextView? = null
        var idx: Int? = null
        init {
            address = view.old_address
            road_address = view.new_address
            road_address_guide = view.new_address_guide
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
        holder.address?.text = maplist[position].address
        if(maplist[position].roadAddress != "") {
            holder.road_address?.visibility = View.VISIBLE
            holder.road_address_guide?.visibility = View.VISIBLE
            holder.road_address?.text = maplist[position].roadAddress
        }
        else{
            holder.road_address?.visibility = View.GONE
            holder.road_address_guide?.visibility = View.GONE
        }
        holder.idx = maplist[position].idx
    }
}