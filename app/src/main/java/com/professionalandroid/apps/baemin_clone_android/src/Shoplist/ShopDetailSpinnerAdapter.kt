package com.professionalandroid.apps.baemin_clone_android.src.Shoplist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.professionalandroid.apps.baemin_clone_android.R
import kotlinx.android.synthetic.main.layout_spinner.view.*

class ShopDetailSpinnerAdapter(): BaseAdapter() {

    lateinit var mcontext: Context
    lateinit var data: List<String>

    constructor(data: List<String>, context: Context): this(){
        this.data = data
        mcontext = context
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        val view = LayoutInflater.from(mcontext).inflate(R.layout.layout_spinner, p2, false)
        val spinnerItem = view.spinnerText
        spinnerItem.text = data[p0]

        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view = LayoutInflater.from(mcontext).inflate(R.layout.layout_spinner_dropdown, parent, false)
        val spinnerItem = view.spinnerText
        spinnerItem.text = data[position]
        val checkBox = view.setOnClickListener {

        }
        return view
    }

    override fun getItem(p0: Int): Any {
        return data[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return data.size
    }
}

