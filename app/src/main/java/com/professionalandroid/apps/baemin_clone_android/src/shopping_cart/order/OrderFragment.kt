package com.professionalandroid.apps.baemin_clone_android.src.shopping_cart.order

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.src.Shoplist.ShoplistActivity
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity.Companion.user_address
import kotlinx.android.synthetic.main.activity_shoplist.*
import kotlinx.android.synthetic.main.fragment_order.view.*

class OrderFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_order, container, false)

        val totalPrice = arguments?.getString("price", "ddd")
        Log.d("test", totalPrice!!)
        (activity as ShoplistActivity).setSupportActionBar(view.shop_order_toolbar)
        (activity as ShoplistActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        (activity as ShoplistActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)



        view.order_addr.text = user_address
        view.order_price.text = totalPrice
        view.order_total1.text = (Integer.parseInt(totalPrice) + 2000).toString()
        view.order_total2.text = (Integer.parseInt(totalPrice) + 2000).toString()

        view.order_submit_btn.setOnClickListener {
            Toast.makeText(context, "주문이 완료되었습니다.", Toast.LENGTH_SHORT).show()
            MainActivity.login_status = false
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
            (activity as ShoplistActivity).finishAffinity()
        }
        return view
    }

}