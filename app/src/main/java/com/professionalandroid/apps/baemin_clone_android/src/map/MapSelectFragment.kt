package com.professionalandroid.apps.baemin_clone_android.src.map

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity
import com.professionalandroid.apps.baemin_clone_android.src.map.interfaces.MapSelectFragmentView
import com.professionalandroid.apps.baemin_clone_android.src.map.models.UserLocation
import com.professionalandroid.apps.baemin_clone_android.src.map.navermap.NaverMapFragment
import kotlinx.android.synthetic.main.fragment_map_select.view.*

class MapSelectFragment : Fragment(), MapSelectFragmentView {

    val mapSelectService = MapSelectService(this)
    val maplist = mutableListOf<UserLocation>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_map_select, container, false)

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

}