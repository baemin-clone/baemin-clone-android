package com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_information

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_information.interfaces.ShopDetailInformationFragmentView
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_information.models.ShopDetailInformationResponse
import kotlinx.android.synthetic.main.fragment_shop_detail_information.*

class ShopDetailInformationFragment : Fragment(), ShopDetailInformationFragmentView {

    val mShopDetailInformationService = ShopDetailInformationService(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_shop_detail_information, container, false)
        val shopidx = arguments?.getInt("shopidx") ?: -1
        mShopDetailInformationService.shopDetailInformation(shopidx)
        return view
    }

    override fun shopDetailInformation(body: ShopDetailInformationResponse) {
        shop_detail_information_description.text = body.result.description
        shop_detail_information_guide.text = body.result.guide
        shop_detail_information_recent.text = body.result.statistics.recentOrderNum
        shop_detail_information_review.text = body.result.statistics.reviewNum
        shop_detail_information_bookmark.text = body.result.statistics.bookmarkNum
        shop_detail_information_operating_time.text = body.result.info.operatingTime
        shop_detail_information_closed_days.text = body.result.info.closedDays
        shop_detail_information_phone.text = body.result.info.phone
        shop_detail_information_delivery_zone.text = body.result.info.deliveryZone
    }

}