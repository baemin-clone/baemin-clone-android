package com.professionalandroid.apps.baemin_clone_android.src.bookmark

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.src.bookmark.interfaces.FirstBookmarkFragmentView
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity
import kotlinx.android.synthetic.main.fragment_bookmark.view.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class BookmarkFragment : Fragment(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_bookmark, container, false)

        var nowPage = view.bookmark_tab_layout.selectedTabPosition
        val firstBookmarkPage = FirstBookmarkFragment()
        val directPaymentPage = DirectPaymentFragment()
        val callPage = CallFragment()
        val ft = (activity as MainActivity).supportFragmentManager

        ft.beginTransaction().replace(R.id.bookmark_fragment, firstBookmarkPage).commit()

        view.bookmark_tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                val newPage = tab?.position
                if (nowPage != newPage){
                    when(newPage){
                        0 -> ft.beginTransaction().replace(R.id.bookmark_fragment, firstBookmarkPage).commit()
                        1 -> ft.beginTransaction().replace(R.id.bookmark_fragment, directPaymentPage).commit()
                        2 -> ft.beginTransaction().replace(R.id.bookmark_fragment, callPage).commit()
                    }
                    nowPage = newPage!!
                }
            }

        })
        return view
    }

}