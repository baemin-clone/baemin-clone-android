package com.professionalandroid.apps.baemin_clone_android.src.shopping_cart

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.src.shopping_cart.models.ShoppingCartItem
import kotlinx.android.synthetic.main.layout_shoppingcart_item.view.*

class ShoppingCartRecyclerViewAdapter():RecyclerView.Adapter<ShoppingCartRecyclerViewAdapter.ViewHolder>() {

    lateinit var itemlist: MutableList<ShoppingCartItem>
    lateinit var mcontext :Context
    lateinit var mlistener: ShoppingCartRecyclerViewAdapter.OnButtonSelected

    constructor(itemlist: MutableList<ShoppingCartItem>, context: Context, listener: OnButtonSelected): this(){
        this.itemlist = itemlist
        mcontext = context
        mlistener = listener
    }

    interface OnButtonSelected{
        fun plus(v:View, position: Int)
        fun minus(v:View, position: Int)
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        var itemtitle: TextView? = null
        var itemprice: TextView? = null
        var itemnum: TextView? = null
        var itemrecyclerview: RecyclerView? = null
        var plusButton: ImageView? = null
        var minusButton: ImageView? = null

        init {
            itemtitle = view.shoppingCart_menu_title
            itemprice = view.shoppingCart_price
            itemnum = view.shoppingCart_item_num
            itemrecyclerview = view.shoppingCart_recyclerview
            plusButton = view.shoppingCart_plus.apply {
                setOnClickListener {
                    mlistener.plus(view, adapterPosition)
                }
            }
            minusButton = view.shoppingCart_minus.apply {
                setOnClickListener {
                    mlistener.minus(view, adapterPosition)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflateview = LayoutInflater.from(parent.context).inflate(R.layout.layout_shoppingcart_item, parent, false)
        return ViewHolder(inflateview)
    }

    override fun getItemCount(): Int {
        return itemlist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val madapter = ShoppingCartOptionRecyclerViewAdapter(itemlist[position].shopping.result.options.toMutableList())
        holder.itemnum?.text = itemlist[position].num.toString()
        holder.itemtitle?.text = itemlist[position].shopping.result.menuTitle
        holder.itemprice?.text = itemlist[position].shopping.result.price.toString()
        holder.itemrecyclerview?.layoutManager = LinearLayoutManager(mcontext)
        holder.itemrecyclerview?.adapter = madapter
        holder.itemrecyclerview?.setHasFixedSize(true)
        madapter.notifyDataSetChanged()
    }
}