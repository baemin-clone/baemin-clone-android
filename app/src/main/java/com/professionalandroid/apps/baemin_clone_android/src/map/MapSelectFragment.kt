package com.professionalandroid.apps.baemin_clone_android.src.map

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.src.map.interfaces.MapSelectFragmentView

class MapSelectFragment : Fragment(), MapSelectFragmentView {

    val mapSelectService = MapSelectService(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_map_select, container, false)

        return view
    }

}