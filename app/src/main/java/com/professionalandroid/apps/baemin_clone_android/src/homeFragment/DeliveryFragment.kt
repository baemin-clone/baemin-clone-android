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
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_menu.shop_item.ShopDetailItemFragment
import kotlinx.android.synthetic.main.fragment_delivery.*
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

        view.home_korea.setOnClickListener(btnClickListener)
        view.home_dduck.setOnClickListener(btnClickListener)
        view.home_cafe.setOnClickListener(btnClickListener)
        view.home_japan.setOnClickListener(btnClickListener)
        view.home_chicken.setOnClickListener(btnClickListener)
        view.home_pizza.setOnClickListener(btnClickListener)
        view.home_asia.setOnClickListener(btnClickListener)
        view.home_china.setOnClickListener(btnClickListener)
        view.home_bossam.setOnClickListener(btnClickListener)
        view.home_night.setOnClickListener(btnClickListener)
        view.home_soup.setOnClickListener(btnClickListener)
        view.home_dosirok.setOnClickListener(btnClickListener)
        view.home_fastfood.setOnClickListener(btnClickListener)
        view.home_franchise.setOnClickListener(btnClickListener)

        return view
    }


    fun movetoShoplist(kind: Int){
        val intent = Intent(activity, ShoplistActivity::class.java)
        intent.putExtra("kind", kind)
        startActivity(intent)
    }
    private val btnClickListener =
        View.OnClickListener { v ->
            when (v.id) {
                R.id.home_korea -> movetoShoplist(0)
                R.id.home_dduck -> movetoShoplist(1)
                R.id.home_cafe -> movetoShoplist(2)
                R.id.home_japan -> movetoShoplist(3)
                R.id.home_chicken -> movetoShoplist(4)
                R.id.home_pizza -> movetoShoplist(5)
                R.id.home_asia -> movetoShoplist(6)
                R.id.home_china -> movetoShoplist(7)
                R.id.home_bossam -> movetoShoplist(8)
                R.id.home_night -> movetoShoplist(9)
                R.id.home_soup -> movetoShoplist(10)
                R.id.home_dosirok -> movetoShoplist(11)
                R.id.home_fastfood -> movetoShoplist(12)
                R.id.home_franchise -> movetoShoplist(13)
            }
        }

}
