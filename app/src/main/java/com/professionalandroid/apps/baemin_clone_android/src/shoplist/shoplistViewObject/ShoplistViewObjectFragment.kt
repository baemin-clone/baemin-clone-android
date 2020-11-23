package com.professionalandroid.apps.baemin_clone_android.src.shoplist.shoplistViewObject

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.src.shoplist.ShoplistActivity
import com.professionalandroid.apps.baemin_clone_android.src.shoplist.shoplistViewObject.interfaces.ShoplistViewObjectFragmentView
import com.professionalandroid.apps.baemin_clone_android.src.shoplist.shoplistViewObject.models.Result
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.ShopDetailFragment
import kotlinx.android.synthetic.main.fragment_shoplist_view_object.view.*

class ShoplistViewObjectFragment(val mlistener: ShopDetailFragment.Itemadd) : Fragment(), ShoplistViewObjectFragmentView, ShoplistViewObjectRecyclerViewAdapter.OnLoadMoreListener {

    private var page = 0       // 현재 페이지
    private var size = 10     // 한 번에 가져올 아이템 수
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var shoplistViewObjectRecyclerViewAdapter: ShoplistViewObjectRecyclerViewAdapter

    private val shoplist = mutableListOf<Result>()
    lateinit var category: String
    var order: String = "상관없음"
    var minAmount: Int? = -1
    var tip: Int? = -1
    var star: Double? = -1.0

    val mShoplistViewObjectService = ShoplistViewObjectService(this)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        shoplistViewObjectRecyclerViewAdapter = ShoplistViewObjectRecyclerViewAdapter(shoplist, this, context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_shoplist_view_object, container, false)
        mRecyclerView = view.shoplist_recyclerview
        mRecyclerView.layoutManager = LinearLayoutManager(context)
        shoplistViewObjectRecyclerViewAdapter.setRecyclerView(mRecyclerView)
        shoplistViewObjectRecyclerViewAdapter.linearLayoutManager = LinearLayoutManager(context)
        mRecyclerView.adapter = shoplistViewObjectRecyclerViewAdapter
        category = arguments?.getString("kind")!!

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadPosts(category)
    }

    private fun loadPosts(category: String){
        mShoplistViewObjectService.getmoreShoplist(category, order, minAmount, tip, star, getPage(), size)
    }

    private fun getPage(): Int {
        page++
        return page
    }

    override fun firstload() {

    }

    override fun loadmore(templist: List<Result>?) {
        if(templist != null){
            for(i in templist){
                shoplist.add(i)
            }
            shoplistViewObjectRecyclerViewAdapter.notifyDataSetChanged()
            shoplistViewObjectRecyclerViewAdapter.isMoreLoading = false
        }
    }

    override fun onLoadMore() {
        mShoplistViewObjectService.getmoreShoplist(category, order, minAmount, tip, star, getPage(), size)
    }

    override fun onItemSeledted(v: View, position: Int) {
        val viewHolder = mRecyclerView.findViewHolderForAdapterPosition(position) as ShoplistViewObjectRecyclerViewAdapter.ViewHolder
        val shopDetailPage = ShopDetailFragment(mlistener).apply {
            arguments = Bundle().apply {
                putInt("shopidx", viewHolder.storeIdx!!)
            }
        }
        (activity as ShoplistActivity).addFragment(shopDetailPage)
    }

}