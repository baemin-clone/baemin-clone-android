package com.professionalandroid.apps.baemin_clone_android.src.myinfoFragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.src.ApplicationClass.Companion.X_ACCESS_TOKEN
import com.professionalandroid.apps.baemin_clone_android.src.ApplicationClass.Companion.sSharedPreferences
import com.professionalandroid.apps.baemin_clone_android.src.login.LoginActivity
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity.Companion.user_nickname
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

        if(sSharedPreferences?.getString(X_ACCESS_TOKEN, null) != null){
            Log.d("teset", "fuck")
            view.myinfo_blank_ads.visibility = View.GONE
            view.myinfo_benefits.visibility = View.VISIBLE
            view.myinfo_detail.text = "고마운분, $user_nickname"
        }
        else{
            Log.d("test", "please")
            view.myinfo_blank_ads.visibility = View.VISIBLE
            view.myinfo_benefits.visibility = View.GONE
            view.myinfo_detail.text = "로그인해주세요"
        }

        view.myinfo_login.setOnClickListener {
            if(sSharedPreferences?.getString(X_ACCESS_TOKEN, null) != null){
                val modifyMyinfoPage = ModifyMyinfoFragment()
                (activity as MainActivity).addFragment(modifyMyinfoPage)
            }
            else{
                val intent = Intent(activity, LoginActivity::class.java)
                startActivity(intent)
            }
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onDestroy() {
        super.onDestroy()
        (activity as MainActivity).showBottomeNav()

    }

    // backbtn on tabbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        (activity as LoginActivity).closeFragemtn(this)

        return super.onOptionsItemSelected(item)
    }

}