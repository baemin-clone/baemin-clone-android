package com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_visit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_visit.interfaces.ShopDetailVisitFragmentView
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_visit.models.Result
import kotlinx.android.synthetic.main.fragment_shop_detail_visit.*

class ShopDetailVisitFragment : Fragment(), ShopDetailVisitFragmentView {

    val mShopDetailVisitService = ShopDetailVisitService(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_shop_detail_visit, container, false)

        val shopidx = arguments?.getInt("shopidx") ?: -1

        mShopDetailVisitService.shopDetailVisit(shopidx)
        return view
    }

    override fun shopDetailVisit(result: Result?) {
        shop_detail_visit_min_price.text = result?.minOrderAmount
        shop_detail_visit_use.text = result?.use
        shop_detail_visit_time.text = result?.cookingTime
        shop_detail_visit_address.text = result?.address
        shop_detail_visit_payment.text = result?.payment
    }

}