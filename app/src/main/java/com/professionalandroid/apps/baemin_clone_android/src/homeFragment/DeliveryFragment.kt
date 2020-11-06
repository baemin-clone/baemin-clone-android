package com.professionalandroid.apps.baemin_clone_android.src.homeFragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.src.Shoplist.ShoplistActivity
import kotlinx.android.synthetic.main.fragment_delivery.view.*

class DeliveryFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_delivery, container, false)
        view.home_delivery_viewpager.apply {
            adapter = SlideViewPagerAdapter(mutableListOf("1번입니다","2번입니다","3번입니다","4번입니다","5번입니다"))
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }

        view.home_korea.setOnClickListener {
            val intent = Intent(activity, ShoplistActivity::class.java)
            startActivity(intent)
        }
        return view
    }


}
