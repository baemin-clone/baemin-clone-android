package com.professionalandroid.apps.baemin_clone_android

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.professionalandroid.apps.baemin_clone_android.login.LoginPage
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

        view.myinfo_login.setOnClickListener {
            val intent = Intent(activity, LoginPage::class.java)
            startActivity(intent)
        }


        return view
    }

}