package com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_menu.shop_item

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.professionalandroid.apps.baemin_clone_android.*
import com.professionalandroid.apps.baemin_clone_android.src.Shoplist.ShoplistActivity
import com.professionalandroid.apps.baemin_clone_android.src.Shoplist.ShoplistActivity.Companion.shoppingCart
import com.professionalandroid.apps.baemin_clone_android.src.Shoplist.ShoplistActivity.Companion.shoppingCartShopIdx
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_menu.shop_item.interfaces.ShopDetailItemFragmentView
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_menu.shop_item.models.Option
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_menu.shop_item.models.ShopDetailItemResponse
import com.professionalandroid.apps.baemin_clone_android.src.shopping_cart.ShoppingCartFragment
import kotlinx.android.synthetic.main.fragment_shop_detail.view.*
import kotlinx.android.synthetic.main.fragment_shop_detail_item.*
import kotlinx.android.synthetic.main.fragment_shop_detail_item.view.*

class ShopDetailItemFragment : Fragment(), ShopDetailItemFragmentView {

    companion object{
        val tempShoppingList = mutableListOf<OptionArray>()
        //val tempShoppingArray = mutableListOf<OptionArray>()
    }

    val mShopDetailItemService = ShopDetailItemService(this)
    var menuIdx: Int? = null
    lateinit var requiredItem: MutableList<Option>
    lateinit var optionalItem: MutableList<Option>
    lateinit var mRequiredRecyclerView: RecyclerView
    lateinit var mRequiredRecyclerViewAdapter: ShopDetailRequiredItemRecyclerViewAdapter
    lateinit var mOptionalRecyclerView: RecyclerView
    lateinit var mOptionalRecyclerViewAdapter: ShopDetailOptionalItemRecyclerViewAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requiredItem = mutableListOf()
        mRequiredRecyclerViewAdapter = ShopDetailRequiredItemRecyclerViewAdapter(requiredItem, context)
        optionalItem = mutableListOf()
        mOptionalRecyclerViewAdapter = ShopDetailOptionalItemRecyclerViewAdapter(optionalItem, context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_shop_detail_item, container, false)

        mRequiredRecyclerView = view.shop_detail_required_recyclerview
        mRequiredRecyclerView.layoutManager = LinearLayoutManager(context)
        mRequiredRecyclerView.adapter = mRequiredRecyclerViewAdapter

        mOptionalRecyclerView = view.shop_detail_optional_recyclerview
        mOptionalRecyclerView.layoutManager = LinearLayoutManager(context)
        mOptionalRecyclerView.adapter = mOptionalRecyclerViewAdapter

        val shopidx = arguments?.getInt("shopidx", 0)
        val menuidx = arguments?.getInt("menuidx", 0)

        mShopDetailItemService.shopDetailItem(menuidx!!)

        view.shop_detail_item_submit_btn.setOnClickListener {
            val tempShoppingListItem = ShoppingItem(menuidx,Integer.parseInt(shop_detail_item_num.text.toString()),tempShoppingList.toMutableList())
            shoppingCartShopIdx = shopidx!!

            shoppingCart.add(tempShoppingListItem)
            tempShoppingList.clear()
            Log.d("test", shoppingCart.joinToString())
            (activity as ShoplistActivity).closeFragment(this)
        }



        return view
    }

    override fun shopDetailItem(body: ShopDetailItemResponse) {
        Glide.with(this)
            .load(body.result?.photoPath)
            .centerCrop()
            .into(shop_detail_item_img)
        shop_detail_item_title.text = body.result?.menuTitle
        shop_detail_item_description.text = body.result?.details
        for(kind in body.result?.options!!){
            if(kind.required!!){
                requiredItem.add(kind)
            }
            else{
                optionalItem.add(kind)
            }
        }
        mRequiredRecyclerViewAdapter.notifyDataSetChanged()
        mOptionalRecyclerViewAdapter.notifyDataSetChanged()
    }

}