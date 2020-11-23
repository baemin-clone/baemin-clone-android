package com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_menu

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.src.shoplist.ShoplistActivity
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_menu.interfaces.ShopDetailMenuFragmentView
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_menu.models.Content
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_menu.models.Menu
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_menu.models.ShopDetailMenuResponse
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_menu.shop_item.ShopDetailItemFragment
import kotlinx.android.synthetic.main.fragment_shop_detail_menu.*
import kotlinx.android.synthetic.main.fragment_shop_detail_menu.view.*

class ShopDetailMenuFragment(val mcontext: ShopDetailItemFragment.ItemAdd) : Fragment(), ShopDetailMenuFragmentView, ShopDetailSignatureRecyclerViewAdapter.ItemSelectedInterface, ShopDetailOtherSubRecyclerViewAdapter.SubItemSelectedInterface {

    val mShopDetailMenuService = ShopDetailMenuService(this)
    var shopidx: Int? = null
    lateinit var signatureMenu :ArrayList<Content>
    lateinit var otherMenu: MutableList<Menu>
    lateinit var mSignatureRecyclerView: RecyclerView
    lateinit var mSignatureRecyclerViewAdapter: ShopDetailSignatureRecyclerViewAdapter
    lateinit var mOtherRecyclerView: RecyclerView
    lateinit var mOtherRecyclerViewAdapter: ShopDetailOtherRecyclerViewAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        signatureMenu = arrayListOf()
        mSignatureRecyclerViewAdapter = ShopDetailSignatureRecyclerViewAdapter(signatureMenu, context, this)

        otherMenu = mutableListOf()
        mOtherRecyclerViewAdapter = ShopDetailOtherRecyclerViewAdapter(otherMenu, context, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_shop_detail_menu, container, false)

        shopidx = arguments?.getInt("shopidx") ?: -1
        mShopDetailMenuService.shopDetailMenu(shopidx!!)

        mSignatureRecyclerView = view.shop_detail_menu_recyclerview1
        mSignatureRecyclerView.layoutManager = LinearLayoutManager(context)
        mSignatureRecyclerView.adapter = mSignatureRecyclerViewAdapter

        mOtherRecyclerView = view.shop_detail_menu_recyclerview2
        mOtherRecyclerView.layoutManager = LinearLayoutManager(context)
        mOtherRecyclerView.adapter = mOtherRecyclerViewAdapter

        return view
    }

    override fun shopDetailMenu(body: ShopDetailMenuResponse) {
        shop_detail_menu_description.text = body.result.description
        // signature menu
        for(kind in body.result.menu){
            if(kind.menuCategory == "대표메뉴"){
                for(j in kind.contents){
                    signatureMenu.add(j)
                }
                mSignatureRecyclerViewAdapter.notifyDataSetChanged()
            }
            else{
                otherMenu.add(kind)
            }
        }
        mOtherRecyclerViewAdapter.notifyDataSetChanged()
        // other menu

    }

    override fun onItemSelected(v: View, position: Int) {
        val viewHolder = mSignatureRecyclerView.findViewHolderForAdapterPosition(position) as ShopDetailSignatureRecyclerViewAdapter.ViewHolder
        val shopDetailItemPage = ShopDetailItemFragment(mcontext).apply {
            arguments = Bundle().apply {
                putInt("shopidx", shopidx!!)
                putInt("menuidx", viewHolder.idx!!)
            }
        }
        (activity as ShoplistActivity).addFragment(shopDetailItemPage)
    }

    override fun onSubItemSelected(view: View, position: Int) {
        val subViewHolder = mOtherRecyclerViewAdapter.listener_recyclerview.findViewHolderForAdapterPosition(position) as ShopDetailOtherSubRecyclerViewAdapter.SubViewHolder
        val shopDetailItemPage = ShopDetailItemFragment(mcontext).apply {
            arguments = Bundle().apply {
                putInt("shopidx", shopidx!!)
                putInt("menuidx", subViewHolder.idx!!)
            }
        }
        (activity as ShoplistActivity).addFragment(shopDetailItemPage)
    }

}