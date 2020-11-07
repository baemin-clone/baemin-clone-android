package com.professionalandroid.apps.baemin_clone_android.src.shopdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.src.Shoplist.ShoplistActivity
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.interfaces.ShopDetailFragmentView
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.models.Result
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_delivery.ShopDetailDeliveryFragment
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_visit.ShopDetailVisitFragment
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_shop_detail.*
import kotlinx.android.synthetic.main.fragment_shop_detail.view.*
import kotlinx.android.synthetic.main.layout_shoplist_item.*

class ShopDetailFragment : Fragment(), ShopDetailFragmentView {

    val mShopDetailService = ShopDetailService(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_shop_detail, container, false)

        var nowPage = view.shop_detail_tab.selectedTabPosition
        val ft = (activity as ShoplistActivity).supportFragmentManager

        val tempidx = 1

        val shopDetailDeliveryPage = ShopDetailDeliveryFragment().apply {
            arguments = Bundle().apply {
                putInt("shopidx", tempidx)
            }
        }
        val shopDetailVisitPage = ShopDetailVisitFragment().apply {
            arguments = Bundle().apply {
                putInt("shopidx", tempidx)
            }
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

        mShopDetailService.getShopDetail(tempidx)

        return view
    }

    override fun shopDetail(result: Result?) {
        shop_detail_title.text = result?.title
        shop_detail_star.text = result?.star.toString()
        shop_detail_review_num.text = result?.reviewNum.toString()
        shop_detail_bookmark_num.text = result?.bookmarkNum.toString()
    }

}