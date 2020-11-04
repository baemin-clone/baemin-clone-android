package com.professionalandroid.apps.baemin_clone_android.src.map

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.professionalandroid.apps.baemin_clone_android.src.map.models.UserLocation

class MapSelectRecyclerViewAdapter(private val maplist:MutableList<UserLocation>):
    RecyclerView.Adapter<MapSelectRecyclerViewAdapter.ViewHolder>(){

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return maplist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}