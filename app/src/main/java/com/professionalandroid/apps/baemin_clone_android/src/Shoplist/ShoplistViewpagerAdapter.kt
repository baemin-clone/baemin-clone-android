package com.professionalandroid.apps.baemin_clone_android.src.Shoplist

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ShoplistViewpagerAdapter (val foodlist: MutableList<String>, fragment: Fragment):
    FragmentStateAdapter(fragment) {

    override fun createFragment( position: Int): Fragment {
        return ShoplistViewPagerFragment(position)
    }
    override fun getItemCount():Int{
        return foodlist.size
    }
}