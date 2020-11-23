package com.professionalandroid.apps.baemin_clone_android.src.shopdetail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.src.shoplist.ShoplistActivity
import com.professionalandroid.apps.baemin_clone_android.src.shoplist.ShoplistActivity.Companion.shoppingCart
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.interfaces.ShopDetailFragmentView
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.models.Result
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shodetail_review.ShopDetailReviewFragment
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_delivery.ShopDetailDeliveryFragment
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_information.ShopDetailInformationFragment
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_menu.ShopDetailMenuFragment
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_menu.shop_item.ShopDetailItemFragment
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_visit.ShopDetailVisitFragment
import com.professionalandroid.apps.baemin_clone_android.src.shopping_cart.ShoppingCartFragment
import kotlinx.android.synthetic.main.fragment_shop_detail.*
import kotlinx.android.synthetic.main.fragment_shop_detail.view.*

class ShopDetailFragment() : Fragment(), ShopDetailFragmentView, ShopDetailItemFragment.ItemAdd {

    val mShopDetailService = ShopDetailService(this)
    lateinit var phone: String
    var mlistener: Itemadd? = null

    constructor(mlistener: Itemadd):this(){
        this.mlistener = mlistener
    }

    interface Itemadd{
        fun floateFab()
}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_shop_detail, container, false)

        (activity as ShoplistActivity).setSupportActionBar(view.shop_detail_toolbar)
        (activity as ShoplistActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        (activity as ShoplistActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if(shoppingCart.isNotEmpty()){
            view.fab_num.visibility = View.VISIBLE
            view.fab_count.text = shoppingCart.size.toString()
        }else{
            view.fab_num.visibility = View.GONE
        }


        var nowPage = view.shop_detail_tab.selectedTabPosition
        val ft = (activity as ShoplistActivity).supportFragmentManager

        val shopidx = arguments?.getInt("shopidx") ?: -1

        val shopDetailDeliveryPage = ShopDetailDeliveryFragment().apply {
            arguments = Bundle().apply {
                putInt("shopidx", shopidx)
            }
        }

        val shopDetailVisitPage = ShopDetailVisitFragment().apply {
            arguments = Bundle().apply {
                putInt("shopidx", shopidx)
            }
        }

        view.shop_detail_bookmark_img.setOnClickListener {
            it.isSelected = !it.isSelected
            mShopDetailService.modifyBookmark(shopidx)
        }

        view.shop_detail_call.setOnClickListener {
            startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone")))
        }

        ft.beginTransaction().replace(R.id.shop_detail_fragment, shopDetailDeliveryPage).commit()

        view.shop_detail_tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                val newPage = tab?.position
                if (nowPage != newPage){
                    when(newPage){
                        0 -> ft.beginTransaction().replace(R.id.shop_detail_fragment, shopDetailDeliveryPage).commit()
                        1 -> ft.beginTransaction().replace(R.id.shop_detail_fragment, shopDetailVisitPage).commit()
                    }
                    nowPage = newPage!!
                }
            }
        })

        val shopDetailMenuPage = ShopDetailMenuFragment(this).apply {
            arguments = Bundle().apply {
                putInt("shopidx", shopidx)
            }
        }
        val shopDetailInformationPage = ShopDetailInformationFragment().apply {
            arguments = Bundle().apply {
                putInt("shopidx", shopidx)
            }
        }
        val shopDetailReviewPage = ShopDetailReviewFragment().apply {
            arguments = Bundle().apply {
                putInt("shopidx", shopidx)
            }
        }

        ft.beginTransaction().replace(R.id.shop_detail_fragment2, shopDetailMenuPage).commit()

        view.shop_detail_item_tab.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                val newPage = tab?.position
                if (nowPage != newPage){
                    when(newPage){
                        0 -> ft.beginTransaction().replace(R.id.shop_detail_fragment2, shopDetailMenuPage).commit()
                        1 -> ft.beginTransaction().replace(R.id.shop_detail_fragment2, shopDetailInformationPage).commit()
                        2 -> ft.beginTransaction().replace(R.id.shop_detail_fragment2, shopDetailReviewPage).commit()
                    }
                    nowPage = newPage!!
                }
            }

        })

        mShopDetailService.getShopDetail(shopidx)

        view.shop_detail_fab.setOnClickListener {
            val shoppingCartPage = ShoppingCartFragment()
            (activity as ShoplistActivity).addFragment(shoppingCartPage)
        }

        return view
    }

    override fun shopDetail(result: Result?) {
        shop_detail_main.text = result?.title
        shop_detail_title.text = result?.title
        shop_detail_star.text = result?.star.toString()
        shop_detail_review_num.text = result?.reviewNum.toString()
        shop_detail_bookmark_num.text = result?.bookmarkNum.toString()
        phone = result?.phone.toString()
    }

    override fun addItem() {
        if(shoppingCart.isNotEmpty()){
            fab_num.visibility = View.VISIBLE
            fab_count.text = shoppingCart.size.toString()
            mlistener?.floateFab()
        }else{
            fab_num.visibility = View.GONE
            mlistener?.floateFab()
        }

    }

}