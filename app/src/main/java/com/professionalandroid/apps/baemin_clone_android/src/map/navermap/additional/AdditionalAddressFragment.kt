package com.professionalandroid.apps.baemin_clone_android.src.map.navermap.additional

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.professionalandroid.apps.baemin_clone_android.NowAddress
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity.Companion.user_address
import com.professionalandroid.apps.baemin_clone_android.src.map.navermap.additional.interfaces.AdditionalAddressFragmentView
import kotlinx.android.synthetic.main.fragment_additional_address.view.*

class AdditionalAddressFragment : Fragment(), AdditionalAddressFragmentView {
    val madditionalAddressService = AdditionalAddressService(this)
    lateinit var tempaddr : String

    interface movetomain1{
        fun move()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_additional_address, container, false)

         val oldaddr = arguments?.getString("addr")
         val newaddr = arguments?.getString("roadaddr")

        view.additional_old_address.text = oldaddr

        if(newaddr == ""){
            view.additional_new_address_guide.visibility = View.GONE
            view.additional_new_address.visibility = View.GONE
        }
        else{
            view.additional_new_address.text = newaddr
        }

        view.additional_submit_btn.setOnClickListener {
            val data = NowAddress(
                newaddr + view.additional_address.text,
                oldaddr + view.additional_address.text,
                arguments?.getDouble("longitude")!!,
                arguments?.getDouble("latitude")!!
            )

            if(arguments?.getBoolean("isAddr")!!){
                tempaddr = oldaddr + view.additional_address.text
            }
            else{
                tempaddr = newaddr + view.additional_address.text
            }

            madditionalAddressService.saveNowAddress(data)

        }

        return view
    }

    override fun movetoMain() {
        user_address = tempaddr
        val ft = (activity as MainActivity).supportFragmentManager
        ft.popBackStack()
        ft.popBackStack()
        val backStackEntryCount: Int = (activity as MainActivity).ft.backStackEntryCount

        val home = (activity as MainActivity).ft.fragments[backStackEntryCount - 2]
        home.onResume()
        (activity as MainActivity).ft.beginTransaction().add(home, "back")

        (activity as MainActivity).closeFragment(this)
        ft.beginTransaction().remove(this).commitNow()

    }



}