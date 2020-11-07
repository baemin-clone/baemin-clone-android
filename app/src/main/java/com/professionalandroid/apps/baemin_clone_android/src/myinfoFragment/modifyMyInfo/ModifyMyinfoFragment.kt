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
import com.professionalandroid.apps.baemin_clone_android.src.ApplicationClass.Companion.X_ACCESS_TOKEN
import com.professionalandroid.apps.baemin_clone_android.src.ApplicationClass.Companion.sSharedPreferences
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

        modifyMyinfoService.getInfo()

        view.modify_myinfo_withdrawal.setOnClickListener {
            modifyMyinfoService.withdrawal()
        }

        return view
    }

    // backbtn on tab bar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) {
            (activity as MainActivity).closeFragment(this)
        }
        return true
    }

    override fun deletejwt() {
        user_status = false
        login_status = true
        sSharedPreferences!!.edit().apply{
            remove(X_ACCESS_TOKEN)
            apply()
        }
        (activity as MainActivity).closeFragment(this)

    }

    override fun putUserdata(nickname: String?, email: String?, phone: String?) {
        modify_myinfo_nickname.setText(nickname)
        modify_myinfo_email.text = email
        modify_myinfo_phone1.text = phone?.slice(IntRange(0,2))
        modify_myinfo_phone2.text = phone?.slice(IntRange(4,6))
        modify_myinfo_phone3.text = phone?.slice(IntRange(8,10))
    }

}