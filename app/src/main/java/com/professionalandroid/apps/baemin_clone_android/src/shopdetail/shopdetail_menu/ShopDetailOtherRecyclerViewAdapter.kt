package com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_menu

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.src.shopdetail.shopdetail_menu.models.Menu
import kotlinx.android.synthetic.main.layout_shop_detail_menu_other_item.view.*

class ShopDetailOtherRecyclerViewAdapter(): RecyclerView.Adapter<ShopDetailOtherRecyclerViewAdapter.ViewHolder>() {

    lateinit var otherMenu: MutableList<Menu>
    lateinit var mcontext: Context
    lateinit var mlistener: ShopDetailOtherSubRecyclerViewAdapter.SubItemSelectedInterface
    lateinit var listener_recyclerview:RecyclerView

    constructor(othermenu: MutableList<Menu>, context: Context, listener: ShopDetailOtherSubRecyclerViewAdapter.SubItemSelectedInterface): this(){
        this.otherMenu = othermenu
        mcontext = context
        mlistener = listener
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val parentview = view
        var other_title: TextView? = null
        var sub_recyclerview: RecyclerView? = null
        init {
            other_title = view.other_item_title
            sub_recyclerview = view.other_recyclerview
            parentview.setOnClickListener {
                if(view.other_recyclerview.visibility == View.VISIBLE){
                    view.other_recyclerview.visibility = View.GONE
                    view.other_item_up_btn.setImageResource(R.drawable.down_arrow)
                }
                else{
                    view.other_recyclerview.visibility = View.VISIBLE
                    view.other_item_up_btn.setImageResource(R.drawable.up_arrow)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflaterview = LayoutInflater.from(parent.context).inflate(R.layout.layout_shop_detail_menu_other_item, parent, false)
        return ViewHolder(inflaterview)
    }

    override fun getItemCount(): Int {
        return otherMenu.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val madapter = ShopDetailOtherSubRecyclerViewAdapter(otherMenu[position].contents, mcontext, mlistener)
        holder.other_title?.text = otherMenu[position].menuCategory
        holder.sub_recyclerview?.layoutManager = LinearLayoutManager(mcontext)
        holder.sub_recyclerview?.adapter = madapter
        holder.sub_recyclerview?.setHasFixedSize(true)
        listener_recyclerview = holder.sub_recyclerview!!
        madapter.notifyDataSetChanged()
    }
}
