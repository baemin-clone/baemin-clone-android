package com.professionalandroid.apps.baemin_clone_android.src.Shoplist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.professionalandroid.apps.baemin_clone_android.R
import kotlinx.android.synthetic.main.fragment_shoplist_view_pager.view.*

class ShoplistViewPagerFragment(val position:Int) : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_shoplist_view_pager, container, false)
        view.texttext.text = position.toString()
        return view
    }

}