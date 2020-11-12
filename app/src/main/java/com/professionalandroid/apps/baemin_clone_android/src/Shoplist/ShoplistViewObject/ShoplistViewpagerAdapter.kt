package com.professionalandroid.apps.baemin_clone_android.src.Shoplist.ShoplistViewObject

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.ShopDetailFragment

class ShoplistViewpagerAdapter (val shoplist: List<String>, val position2: Int, fragment: Fragment, val mlistener: ShopDetailFragment.Itemadd):
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = shoplist.size
    override fun createFragment(position: Int): Fragment {

        Log.d("test", position2.toString()  )
        // Return a NEW fragment instance in createFragment(int)
        val fragment =
            ShoplistViewObjectFragment(mlistener)
        fragment.arguments = Bundle().apply {
            // Our object is just an integer :-P
            putString("kind", shoplist[position])
        }
        return fragment
    }
}
