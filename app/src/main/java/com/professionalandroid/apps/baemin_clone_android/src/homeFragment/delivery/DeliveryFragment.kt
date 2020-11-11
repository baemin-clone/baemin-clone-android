package com.professionalandroid.apps.baemin_clone_android.src.homeFragment.delivery

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.src.Shoplist.ShoplistActivity
import com.professionalandroid.apps.baemin_clone_android.src.homeFragment.SlideViewPagerAdapter
import com.professionalandroid.apps.baemin_clone_android.src.homeFragment.delivery.interfaces.DeliveryFragmentView
import com.professionalandroid.apps.baemin_clone_android.src.homeFragment.delivery.models.DeliveryResponse
import com.professionalandroid.apps.baemin_clone_android.src.homeFragment.delivery.models.RecommendationResponse
import com.professionalandroid.apps.baemin_clone_android.src.homeFragment.delivery.models.Result
import com.professionalandroid.apps.baemin_clone_android.src.homeFragment.delivery.models.Result1
import kotlinx.android.synthetic.main.fragment_delivery.view.*

class DeliveryFragment : Fragment(), DeliveryFragmentView {

    val mDeliveryService = DeliveryService(this)

    lateinit var mBrandRecyclerView: RecyclerView
    lateinit var mBrandRecyclerViewAdapter: DeliveryRecyclerViewAdapter
    lateinit var mRecommendationRecyclerView1: RecyclerView
    lateinit var mRecommendationRecyclerView1Adapter: DeliveryRecommendation1RecyclerViewAdapter
    lateinit var mRecommendationRecyclerView2: RecyclerView
    lateinit var mRecommendationRecyclerView2Adapter: DeliveryRecommendation2RecyclerViewAdapter

    var brandList = mutableListOf<Result>()
    var recommendation1List = mutableListOf<Result1>()
    var recommendation2List = mutableListOf<Result1>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mBrandRecyclerViewAdapter = DeliveryRecyclerViewAdapter(brandList, context)
        mRecommendationRecyclerView1Adapter = DeliveryRecommendation1RecyclerViewAdapter(recommendation1List, context)
        mRecommendationRecyclerView2Adapter = DeliveryRecommendation2RecyclerViewAdapter(recommendation1List, context)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_delivery, container, false)

        mBrandRecyclerView = view.brand_recyclerview
        mBrandRecyclerView.layoutManager = LinearLayoutManager(context).also { it.orientation = LinearLayoutManager.HORIZONTAL }
        mBrandRecyclerView.adapter = mBrandRecyclerViewAdapter

        mRecommendationRecyclerView1 = view.recommendation1_recyclerview
        mRecommendationRecyclerView1.layoutManager = LinearLayoutManager(context).also { it.orientation = LinearLayoutManager.HORIZONTAL }
        mRecommendationRecyclerView1.adapter = mRecommendationRecyclerView1Adapter

        mRecommendationRecyclerView1 = view.recommendation2_recyclerview
        mRecommendationRecyclerView1.layoutManager = LinearLayoutManager(context)
        mRecommendationRecyclerView1.adapter = mRecommendationRecyclerView2Adapter

        view.home_delivery_viewpager.apply {
            adapter =
                SlideViewPagerAdapter(
                    mutableListOf(
                        R.drawable.ads1,
                        R.drawable.ads2,
                        R.drawable.ads3,
                        R.drawable.ads4,
                        R.drawable.ads5,
                        R.drawable.ads6,
                        R.drawable.ads7,
                        R.drawable.ads8,
                        R.drawable.ads9,
                        R.drawable.ads10,
                        R.drawable.ads11,
                        R.drawable.ads12
                    )
                )
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }

        view.home_korea.setOnClickListener(btnClickListener)
        view.home_dduck.setOnClickListener(btnClickListener)
        view.home_cafe.setOnClickListener(btnClickListener)
        view.home_japan.setOnClickListener(btnClickListener)
        view.home_chicken.setOnClickListener(btnClickListener)
        view.home_pizza.setOnClickListener(btnClickListener)
        view.home_asia.setOnClickListener(btnClickListener)
        view.home_china.setOnClickListener(btnClickListener)
        view.home_bossam.setOnClickListener(btnClickListener)
        view.home_night.setOnClickListener(btnClickListener)
        view.home_soup.setOnClickListener(btnClickListener)
        view.home_dosirok.setOnClickListener(btnClickListener)
        view.home_fastfood.setOnClickListener(btnClickListener)
        view.home_franchise.setOnClickListener(btnClickListener)

        mDeliveryService.brand()
        mDeliveryService.recommendation1("햄버거와피자")
        mDeliveryService.recommendation2("맥주와안주")

        return view
    }


    fun movetoShoplist(kind: Int){
        val intent = Intent(activity, ShoplistActivity::class.java)
        intent.putExtra("kind", kind)
        startActivity(intent)
    }
    private val btnClickListener =
        View.OnClickListener { v ->
            when (v.id) {
                R.id.home_korea -> movetoShoplist(0)
                R.id.home_dduck -> movetoShoplist(1)
                R.id.home_cafe -> movetoShoplist(2)
                R.id.home_japan -> movetoShoplist(3)
                R.id.home_chicken -> movetoShoplist(4)
                R.id.home_pizza -> movetoShoplist(5)
                R.id.home_asia -> movetoShoplist(6)
                R.id.home_china -> movetoShoplist(7)
                R.id.home_bossam -> movetoShoplist(8)
                R.id.home_night -> movetoShoplist(9)
                R.id.home_soup -> movetoShoplist(10)
                R.id.home_dosirok -> movetoShoplist(11)
                R.id.home_fastfood -> movetoShoplist(12)
                R.id.home_franchise -> movetoShoplist(13)
            }
        }

    override fun brand(body: DeliveryResponse) {
        brandList.addAll(body.result)
        mBrandRecyclerViewAdapter.notifyDataSetChanged()
    }

    override fun recommendation1(body: RecommendationResponse) {
        recommendation1List.addAll(body.result)
        mRecommendationRecyclerView1Adapter.notifyDataSetChanged()
    }

    override fun recommendation2(body: RecommendationResponse) {
        recommendation2List.addAll(body.result)
        mRecommendationRecyclerView2Adapter.notifyDataSetChanged()
    }

}
