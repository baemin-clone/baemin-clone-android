package com.professionalandroid.apps.baemin_clone_android.src.Shoplist

import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.src.BaseActivity
import kotlinx.android.synthetic.main.activity_shoplist.*

class ShoplistActivity : BaseActivity() {

//    val tabLayoutTextArray = mutableListOf<String>(this.getString(R.string.korea),
//        this.getString(R.string.dduck),
//        this.getString(R.string.japan),
//        this.getString(R.string.chicken),
//        this.getString(R.string.pizza),
//        this.getString(R.string.asia),
//        "중식",
//        this. getString(R.string.bossam),
//        this.getString(R.string.night),
//        this.getString(R.string.soup),
//        this.getString(R.string.dosirok),
//        this.getString(R.string.cafe),
//        this.getString(R.string.fastfood))
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shoplist)

        TabLayoutMediator(shoplist_tab,shoplist_viewpager){tab,position->
            //tab.text = tabLayoutTextArray[position]
        }.attach()

    }
}