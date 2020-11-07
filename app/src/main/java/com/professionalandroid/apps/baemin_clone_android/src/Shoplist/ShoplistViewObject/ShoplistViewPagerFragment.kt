package com.professionalandroid.apps.baemin_clone_android.src.Shoplist.ShoplistViewObject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.professionalandroid.apps.baemin_clone_android.R
import kotlinx.android.synthetic.main.fragment_shoplist_view_pager.view.*

class ShoplistViewPagerFragment() : Fragment() {
    // When requested, this adapter returns a DemoObjectFragment,
    // representing an object in the collection.
    private lateinit var shoplistviewpagerAdapter: ShoplistViewpagerAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shoplist_view_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val tabLayoutTextArray =
            mutableListOf<String>(
                getString(R.string.korea),
                getString(R.string.dduck),
                getString(R.string.japan),
                getString(R.string.chicken),
                getString(R.string.pizza),
                getString(R.string.asia),
                "중식",
                getString(R.string.bossam),
                getString(R.string.night),
                getString(R.string.soup),
                getString(R.string.dosirok),
                getString(R.string.cafe),
                getString(R.string.fastfood))

        shoplistviewpagerAdapter =
            ShoplistViewpagerAdapter(
                tabLayoutTextArray,
                this
            )
        viewPager = view.shoplist_viewpager
        viewPager.adapter = shoplistviewpagerAdapter

        TabLayoutMediator(view.shoplist_tab, viewPager) { tab, position ->
            tab.text = tabLayoutTextArray[position]
        }.attach()
    }
}