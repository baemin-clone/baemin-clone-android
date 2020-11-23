package com.professionalandroid.apps.baemin_clone_android.src.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.src.shoplist.ShoplistActivity.Companion.shoppingCart
import com.professionalandroid.apps.baemin_clone_android.src.home.delivery.DeliveryFragment
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity.Companion.user_address
import com.professionalandroid.apps.baemin_clone_android.src.map.MapSelectFragment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        home_address.text = user_address
        Log.d("test", "home_resume")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)


        val deliveryPage = DeliveryFragment()
        val visitPage = VisitFragment()
        var nowPage = view.home_tab.selectedTabPosition

        val ft = (activity as MainActivity).supportFragmentManager
        ft.beginTransaction().replace(R.id.home_container, deliveryPage).commit()

        view.home_tab.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                val newPage = tab?.position
                if (nowPage != newPage){
                    when(newPage){
                        0 -> ft.beginTransaction().replace(R.id.home_container, deliveryPage).commit()
                        1 -> ft.beginTransaction().replace(R.id.home_container, visitPage).commit()
                    }
                    nowPage = newPage!!
                }
            }

        })

        if(shoppingCart.isNotEmpty()){
            view.home_fab.visibility = View.VISIBLE
            view.home_fab_count.text = shoppingCart.size.toString()
        }
        else{
            view.home_fab.visibility = View.GONE
        }


        view.home_address.setOnClickListener {
            val mapselectPage = MapSelectFragment()
            (activity as MainActivity).addFragment(mapselectPage)
        }

        return view
    }


}