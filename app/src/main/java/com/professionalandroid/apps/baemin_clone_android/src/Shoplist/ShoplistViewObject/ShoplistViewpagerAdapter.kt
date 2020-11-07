package com.professionalandroid.apps.baemin_clone_android.src.Shoplist.ShoplistViewObject

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ShoplistViewpagerAdapter (val shoplist: MutableList<String>, fragment: Fragment):
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = shoplist.size
    override fun createFragment(position: Int): Fragment {
        // Return a NEW fragment instance in createFragment(int)
        val fragment =
            ShoplistViewObjectFragment()
        fragment.arguments = Bundle().apply {
            // Our object is just an integer :-P
            putString("ARG_OBJECT", shoplist[position])
        }
        return fragment
    }
}
