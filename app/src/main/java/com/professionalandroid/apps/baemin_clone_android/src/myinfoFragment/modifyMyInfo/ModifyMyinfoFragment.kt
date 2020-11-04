package com.professionalandroid.apps.baemin_clone_android.src.myinfoFragment.modifyMyInfo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity.Companion.login_status
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity.Companion.user_status
import com.professionalandroid.apps.baemin_clone_android.src.myinfoFragment.modifyMyInfo.interfaces.ModifyMyinfoFragmentView
import kotlinx.android.synthetic.main.fragment_modify_myinfo.*
import kotlinx.android.synthetic.main.fragment_modify_myinfo.view.*

class ModifyMyinfoFragment : Fragment(), ModifyMyinfoFragmentView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_modify_myinfo, container, false)
        (activity as MainActivity).setSupportActionBar(modify_myinfo_toolbar)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val modifyMyinfoService = ModifyMyinfoService(this)

        view.modify_myinfo_withdrawal.setOnClickListener {
            modifyMyinfoService.withdrawal()
        }

        return view
    }

    // backbtn on tab bar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun deletejwt() {
        user_status = false
        login_status = true
        val ss = (activity as MainActivity).getSharedPreferences("sSharedPreferences", Context.MODE_PRIVATE)
        ss.edit().apply{
            remove("TAG")
            apply()
        }
        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)
    }

}