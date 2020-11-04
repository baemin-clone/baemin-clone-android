package com.professionalandroid.apps.baemin_clone_android.src.myinfoFragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.src.login.LoginActivity
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity.Companion.user_nickname
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity.Companion.user_status
import com.professionalandroid.apps.baemin_clone_android.src.myinfoFragment.modifyMyInfo.ModifyMyinfoFragment
import kotlinx.android.synthetic.main.fragment_myinfo.view.*

class MyinfoFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_myinfo, container, false)

        (activity as MainActivity).setSupportActionBar(view.myinfo_toolbar)

        if(user_status){
            view.myinfo_blank_ads.visibility = View.GONE
            view.myinfo_benefits.visibility = View.VISIBLE
            view.myinfo_detail.text = "고마운분, $user_nickname"
            view.myinfo_login.setOnClickListener {
                Log.d("test", "tt")
                val modifyMyinfoPage = ModifyMyinfoFragment()
                (activity as MainActivity).addFragment(modifyMyinfoPage)
            }
        }
        else{
            view.myinfo_login.setOnClickListener {
                val intent = Intent(activity, LoginActivity::class.java)
                startActivity(intent)
            }
        }

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity as MainActivity).showBottomeNav()

    }

}