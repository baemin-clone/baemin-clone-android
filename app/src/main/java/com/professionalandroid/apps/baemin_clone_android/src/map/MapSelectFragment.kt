package com.professionalandroid.apps.baemin_clone_android.src.map

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity
import com.professionalandroid.apps.baemin_clone_android.src.map.interfaces.MapSelectFragmentView
import com.professionalandroid.apps.baemin_clone_android.src.map.models.UserLocation
import com.professionalandroid.apps.baemin_clone_android.src.map.navermap.NaverMapFragment
import kotlinx.android.synthetic.main.fragment_map_select.view.*

class MapSelectFragment : Fragment(), MapSelectFragmentView, MapSelectRecyclerViewAdapter.OnListItemSelectedInterface {

    val mapSelectService = MapSelectService(this)
    val maplist = mutableListOf<UserLocation>()
    lateinit var mMapSelectRecyclerView: RecyclerView
    lateinit var mMapSelectRecyclerViewAdapter: MapSelectRecyclerViewAdapter

    override fun onResume() {
        super.onResume()
        Log.d("test", "mapselect_resume")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_map_select, container, false)

        // Connect RecyclerView
        mMapSelectRecyclerView = view.map_select_recycler_view
        mMapSelectRecyclerViewAdapter = MapSelectRecyclerViewAdapter(this, maplist)
        mMapSelectRecyclerView.layoutManager = LinearLayoutManager(context)
        mMapSelectRecyclerView.adapter = mMapSelectRecyclerViewAdapter

        mapSelectService.getMapList(maplist)

        view.map_select_now.setOnClickListener {
            val naverMapPage = NaverMapFragment()
            (activity as MainActivity).addFragment(naverMapPage)
        }

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity as MainActivity).showBottomeNav()
    }

    override fun addMaptoList(maplist: List<UserLocation>) {
        this.maplist.addAll(maplist)
        mMapSelectRecyclerViewAdapter.notifyDataSetChanged()
    }

    override fun onItemDeleted(v: View, position: Int) {
        val viewHolder = mMapSelectRecyclerView.findViewHolderForAdapterPosition(position) as MapSelectRecyclerViewAdapter.ViewHolder
        for(i in maplist.indices){
            if(maplist[i].idx == viewHolder.idx){
                mapSelectService.deleteMap(viewHolder.idx!!)
                maplist.removeAt(i)
                break
            }
        }
        mMapSelectRecyclerViewAdapter.notifyDataSetChanged()
    }


}