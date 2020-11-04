package com.professionalandroid.apps.baemin_clone_android.src.map.navermap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.professionalandroid.apps.baemin_clone_android.R
import kotlinx.android.synthetic.main.fragment_naver_map.view.*


class NaverMapFragment : Fragment(),OnMapReadyCallback {

    var mapView: MapView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_naver_map, container, false)

        mapView = view.naver_map
        mapView?.onCreate(savedInstanceState)
        naverMapBasicSettings()

        return view
    }

    fun naverMapBasicSettings(){
        mapView?.getMapAsync(this)
    }

    override fun onMapReady(naverMap: NaverMap) {

        val uiSettings = naverMap.uiSettings
        uiSettings.isLocationButtonEnabled = true

        naverMap.mapType = NaverMap.MapType.Basic
    }

}