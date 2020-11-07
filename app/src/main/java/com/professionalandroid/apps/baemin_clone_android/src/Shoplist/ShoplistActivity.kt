package com.professionalandroid.apps.baemin_clone_android.src.Shoplist

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.src.Shoplist.ShoplistViewObject.ShoplistViewPagerFragment
import com.professionalandroid.apps.baemin_clone_android.src.Shoplist.ShoplistViewObject.ShoplistViewpagerAdapter

class ShoplistActivity : FragmentActivity() {

    private lateinit var shoplistViewpagerAdapter: ShoplistViewpagerAdapter
    private lateinit var shoplistViewpager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shoplist)

        val shoplistViewPagerFragment =
            ShoplistViewPagerFragment()
        supportFragmentManager.beginTransaction().replace(R.id.shoplist_fragment, shoplistViewPagerFragment).commit()

    }

    fun addFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.shoplist_layout, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun closeFragemtn(fragment: Fragment){
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.remove(fragment)
        transaction.commit()
        manager.popBackStack()
    }
}