package com.professionalandroid.apps.baemin_clone_android.src.Shoplist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.ShoppingItem
import com.professionalandroid.apps.baemin_clone_android.src.BaseActivity
import com.professionalandroid.apps.baemin_clone_android.src.Shoplist.ShoplistViewObject.ShoplistViewPagerFragment
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.ShopDetailFragment
import com.professionalandroid.apps.baemin_clone_android.src.shopping_cart.ShoppingCartFragment
import kotlinx.android.synthetic.main.activity_shoplist.*

class ShoplistActivity() : BaseActivity(), ShopDetailFragment.Itemadd {

    companion object{
        var shoppingCartShopIdx: Int = 0
        val shoppingCart = mutableListOf<ShoppingItem>()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shoplist)

        if(intent.getIntExtra("shopidx", -1) != -1){
            val shopDetailPage = ShopDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt("shopidx", intent.getIntExtra("shopidx", -1))
                }
            }
            addFragment(shopDetailPage)
        }



        val sort = listOf<String>("기본 순", "가까운 순", "주문 많은 순", "별점 높은 순", "찜 많은 순")
        val min_price = listOf<String>("전체", "5,000원 이하", "10,000 이하", "12,000 이하", "1,5000원 이하", "20,000원 이하")
        val tip = listOf<String>("전체", "무료", "1,000원 이하", "2,000이하", "3,000원 이하")
        shoplist_sort.adapter = ShopDetailSpinnerAdapter(sort, this)
        shoplist_min_order_price.adapter = ShopDetailSpinnerAdapter(min_price, this)
        shoplist_tip.adapter = ShopDetailSpinnerAdapter(tip, this)

        val tabLayoutTextArray =
            listOf<String>(
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
                getString(R.string.fastfood),
                getString(R.string.franchise))

        setSupportActionBar(shoplist_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val kind = intent.getIntExtra("kind", 0)

        shoplist_name.text = tabLayoutTextArray[kind]

        if(shoppingCart.isNotEmpty()){
            shoplist_fab.visibility = View.VISIBLE
            shoplist_fab_count.text = shoppingCart.size.toString()
        }
        else{
            shoplist_fab.visibility = View.GONE
        }


        val shoplistViewPagerFragment =
            ShoplistViewPagerFragment(this).apply {
                arguments = Bundle().apply {
                    putInt("kind", kind)
                }
            }
        supportFragmentManager.beginTransaction().replace(R.id.shoplist_fragment, shoplistViewPagerFragment).commit()

    }

    fun addFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
        transaction.add(R.id.shoplist_layout, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun closeFragment(fragment: Fragment){
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
        transaction.remove(fragment)
        transaction.commit()
        manager.popBackStack()
    }

    override fun floateFab() {
        if(shoppingCart.isNotEmpty()){
            shoplist_fab.apply {
                setOnClickListener {
                    val shoppingCartPage = ShoppingCartFragment()
                    addFragment(shoppingCartPage)
                }
                visibility = View.VISIBLE}
            shoplist_fab_count.text = shoppingCart.size.toString()

        }
        else{
            shoplist_fab.visibility = View.GONE
        }
    }
}