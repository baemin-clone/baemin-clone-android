package com.professionalandroid.apps.baemin_clone_android.src.shopping_cart

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.professionalandroid.apps.baemin_clone_android.MenuItem
import com.professionalandroid.apps.baemin_clone_android.Order
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.ShoppingCart
import com.professionalandroid.apps.baemin_clone_android.src.Shoplist.ShoplistActivity
import com.professionalandroid.apps.baemin_clone_android.src.Shoplist.ShoplistActivity.Companion.shoppingCart
import com.professionalandroid.apps.baemin_clone_android.src.Shoplist.ShoplistActivity.Companion.shoppingCartShopIdx
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity.Companion.login_status
import com.professionalandroid.apps.baemin_clone_android.src.shopping_cart.interfaces.ShoppingCartFragmentView
import com.professionalandroid.apps.baemin_clone_android.src.shopping_cart.models.OrderResponse
import com.professionalandroid.apps.baemin_clone_android.src.shopping_cart.models.ShoppingCartItem
import com.professionalandroid.apps.baemin_clone_android.src.shopping_cart.models.ShoppingCartItemResponse
import com.professionalandroid.apps.baemin_clone_android.src.shopping_cart.models.ShoppingCartShopResponse
import kotlinx.android.synthetic.main.fragment_shopping_cart.*
import kotlinx.android.synthetic.main.fragment_shopping_cart.view.*

class ShoppingCartFragment : Fragment(), ShoppingCartFragmentView {

    val mShoppingCartService = ShoppingCartService(this)
    val cartlist = mutableListOf<ShoppingCartItem>()
    lateinit var mShoppingCartRecyclerView: RecyclerView
    lateinit var mShoppingCartRecyclerViewAdapter: ShoppingCartRecyclerViewAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mShoppingCartRecyclerViewAdapter = ShoppingCartRecyclerViewAdapter(cartlist, context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_shopping_cart, container, false)

        mShoppingCartRecyclerView = view.shoppingCart_item_recyclerview
        mShoppingCartRecyclerView.layoutManager = LinearLayoutManager(context)
        mShoppingCartRecyclerView.adapter = mShoppingCartRecyclerViewAdapter

        mShoppingCartService.shoppingCartTitle(shoppingCartShopIdx)

        for(i in shoppingCart){
            val temp = ShoppingCart(shoppingCartShopIdx, i.menuidx, i.optionArray)
            val num = i.menuNum
            Log.d("test", temp.toString())
            mShoppingCartService.shoppingCartItem(temp, num)
        }

        view.shoppingCart_submit_btn.setOnClickListener {

            val temp2 = mutableListOf<MenuItem>()
            for(i in shoppingCart){

                val temp3 = mutableListOf<Int>()
                for(j in i.optionArray){
                    temp3.addAll(j.options)
                }
                temp2.add(MenuItem(i.menuidx, i.menuNum, temp3))
            }
            val temp1 = Order(shoppingCartShopIdx, "배달", temp2)
            mShoppingCartService.order(temp1)
        }

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        shoppingCart.clear()
    }

    override fun shoppingCartTitle(body: ShoppingCartShopResponse) {
        shoppingCart_shop_title.text = body.storeTitle
    }

    override fun shoppingCartItem(body: ShoppingCartItemResponse, num: Int) {
        cartlist.add(ShoppingCartItem(body, num))
        mShoppingCartRecyclerViewAdapter.notifyDataSetChanged()

    }

    override fun order(body: OrderResponse) {
        Toast.makeText(context, "주문이 완료되었습니다.", Toast.LENGTH_SHORT).show()
        login_status = false
        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)
        (activity as ShoplistActivity).finishAffinity()
    }


}