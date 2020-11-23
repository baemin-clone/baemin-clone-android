package com.professionalandroid.apps.baemin_clone_android.src.shopping_cart

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.professionalandroid.apps.baemin_clone_android.MenuItem
import com.professionalandroid.apps.baemin_clone_android.Order
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.ShoppingCart
import com.professionalandroid.apps.baemin_clone_android.src.shoplist.ShoplistActivity
import com.professionalandroid.apps.baemin_clone_android.src.shoplist.ShoplistActivity.Companion.shoppingCart
import com.professionalandroid.apps.baemin_clone_android.src.shoplist.ShoplistActivity.Companion.shoppingCartShopIdx
import com.professionalandroid.apps.baemin_clone_android.src.shopping_cart.interfaces.ShoppingCartFragmentView
import com.professionalandroid.apps.baemin_clone_android.src.shopping_cart.models.ShoppingCartItem
import com.professionalandroid.apps.baemin_clone_android.src.shopping_cart.models.ShoppingCartItemResponse
import com.professionalandroid.apps.baemin_clone_android.src.shopping_cart.models.ShoppingCartShopResponse
import com.professionalandroid.apps.baemin_clone_android.src.shopping_cart.order.OrderFragment
import kotlinx.android.synthetic.main.fragment_shopping_cart.*
import kotlinx.android.synthetic.main.fragment_shopping_cart.view.*

class ShoppingCartFragment : Fragment(), ShoppingCartFragmentView, ShoppingCartRecyclerViewAdapter.OnButtonSelected {

    val mShoppingCartService = ShoppingCartService(this)
    val cartlist = mutableListOf<ShoppingCartItem>()
    lateinit var mShoppingCartRecyclerView: RecyclerView
    lateinit var mShoppingCartRecyclerViewAdapter: ShoppingCartRecyclerViewAdapter
    var totalprice = 0

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mShoppingCartRecyclerViewAdapter = ShoppingCartRecyclerViewAdapter(cartlist, context, this)
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

        var totalnum = 0
        for(i in shoppingCart){
            val temp = ShoppingCart(shoppingCartShopIdx, i.menuidx, i.optionArray)
            val num = i.menuNum
            Log.d("test", temp.toString())
            totalnum += num
            mShoppingCartService.shoppingCartItem(temp, num)
        }

        view.shoppingCart_num.text = totalnum.toString()

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

            val orderPage = OrderFragment().apply {
                arguments = Bundle().apply {
                    putString("price", view.shoppingCart_total_price.text.toString())
                }
            }
            (activity as ShoplistActivity).addFragment(orderPage)

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
        totalprice += body.result.price
        shoppingCart_total_price.text = totalprice.toString()
        shoppingCart_total_price2.text = totalprice.toString()
        cartlist.add(ShoppingCartItem(body, num))
        mShoppingCartRecyclerViewAdapter.notifyDataSetChanged()


    }

    override fun plus(v: View, position: Int) {
        val viewHolder = mShoppingCartRecyclerView.findViewHolderForAdapterPosition(position) as ShoppingCartRecyclerViewAdapter.ViewHolder
        val num = Integer.parseInt(viewHolder.itemnum?.text.toString())
        val price = Integer.parseInt(viewHolder.itemprice?.text.toString()) / num
        viewHolder.itemnum?.text = (num + 1).toString()
        viewHolder.itemprice?.text = (Integer.parseInt(viewHolder.itemprice?.text.toString()) + price).toString()
        viewHolder.minusButton?.setImageResource(R.drawable.minus_down)
        shoppingCart_num.text = (Integer.parseInt(shoppingCart_num.text.toString()) + 1).toString()
        shoppingCart_total_price2.text = (Integer.parseInt(shoppingCart_total_price2.text.toString()) + price).toString()
        shoppingCart_total_price.text = (Integer.parseInt(shoppingCart_total_price.text.toString()) + price).toString()
    }

    override fun minus(v: View, position: Int) {
        val viewHolder = mShoppingCartRecyclerView.findViewHolderForAdapterPosition(position) as ShoppingCartRecyclerViewAdapter.ViewHolder
        val num = Integer.parseInt(viewHolder.itemnum?.text.toString())
        val price = Integer.parseInt(viewHolder.itemprice?.text.toString()) / num
        if(num > 1){
            viewHolder.minusButton?.setImageResource(R.drawable.minus_down)
            viewHolder.itemnum?.text = (num - 1).toString()
            viewHolder.itemprice?.text = (Integer.parseInt(viewHolder.itemprice?.text.toString()) - price).toString()
            shoppingCart_num.text = (Integer.parseInt(shoppingCart_num.text.toString()) - 1).toString()
            shoppingCart_total_price2.text = (Integer.parseInt(shoppingCart_total_price2.text.toString()) - price).toString()
            shoppingCart_total_price.text = (Integer.parseInt(shoppingCart_total_price.text.toString()) - price).toString()
        }
        else{
            viewHolder.minusButton?.setImageResource(R.drawable.minus_up)
        }

    }


}