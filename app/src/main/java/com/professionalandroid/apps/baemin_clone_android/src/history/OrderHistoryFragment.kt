package com.professionalandroid.apps.baemin_clone_android.src.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity
import kotlinx.android.synthetic.main.fragment_bookmark.view.*
import kotlinx.android.synthetic.main.fragment_order_history.view.*

class OrderHistoryFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_order_history, container, false)

        var nowPage = view.history_tab_layout.selectedTabPosition
        val historyPage = HistoryFragment()
        val bmartPage = OrderBmartFragment()
        val ft = (activity as MainActivity).supportFragmentManager

        ft.beginTransaction().replace(R.id.order_history_fragment, historyPage).commit()

        view.history_tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                val newPage = tab?.position
                if (nowPage != newPage){
                    when(newPage){
                        0 -> ft.beginTransaction().replace(R.id.order_history_fragment, historyPage).commit()
                        1 -> ft.beginTransaction().replace(R.id.order_history_fragment, bmartPage).commit()
                    }
                    nowPage = newPage!!
                }
            }

        })
        return view
    }

}