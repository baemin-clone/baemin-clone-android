package com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_delivery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_delivery.interfaces.ShopDetailDeliveryFragmentView
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_delivery.models.Result
import kotlinx.android.synthetic.main.fragment_shop_detail_delivery.*

class ShopDetailDeliveryFragment : Fragment(), ShopDetailDeliveryFragmentView {

    val mshopDetailDeliveryService = ShopDetailDeliveryService(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_shop_detail_delivery, container, false)

        val shopidx = arguments?.getInt("shopidx") ?: -1
        mshopDetailDeliveryService.shopDetailDelivery(shopidx)

        return view
    }

    override fun shopDetailDelivery(result: Result?) {
        shop_detail_delivery_min_price.text = result?.minOrderAmount
        shop_detail_delivery_payment.text = result?.payment
        shop_detail_delivery_time.text = result?.deliveryTime
        shop_detail_delivery_tip.text = result?.deliveryTip
    }


}