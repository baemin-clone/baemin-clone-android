package com.professionalandroid.apps.baemin_clone_android.src.Shoplist

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.ShoppingItem
import com.professionalandroid.apps.baemin_clone_android.src.BaseActivity
import com.professionalandroid.apps.baemin_clone_android.src.Shoplist.ShoplistViewObject.ShoplistViewPagerFragment
import com.professionalandroid.apps.baemin_clone_android.src.Shoplist.ShoplistViewObject.ShoplistViewpagerAdapter
import kotlinx.android.synthetic.main.activity_shoplist.*

class ShoplistActivity : BaseActivity() {

    companion object{
        var shoppingCartShopIdx: Int = 0
        val shoppingCart = mutableListOf<ShoppingItem>()
    }

    private lateinit var shoplistViewpagerAdapter: ShoplistViewpagerAdapter
    private lateinit var shoplistViewpager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shoplist)


        setSupportActionBar(shoplist_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val shoplistViewPagerFragment =
            ShoplistViewPagerFragment().apply {
                arguments = Bundle().apply {
                    putInt("kind", intent.getIntExtra("kind", -1))
                }
            }
        supportFragmentManager.beginTransaction().replace(R.id.shoplist_fragment, shoplistViewPagerFragment).commit()

    }

    fun addFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.shoplist_layout, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun closeFragment(fragment: Fragment){
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.remove(fragment)
        transaction.commit()
        manager.popBackStack()
    }
}