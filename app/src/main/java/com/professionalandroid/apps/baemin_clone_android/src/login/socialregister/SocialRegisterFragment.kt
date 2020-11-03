package com.professionalandroid.apps.baemin_clone_android.src.login.socialregister

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.src.login.LoginActivity
import kotlinx.android.synthetic.main.fragment_social_register.*
import kotlinx.android.synthetic.main.fragment_social_register.view.*

class SocialRegisterFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_social_register, container, false)

        (activity as LoginActivity).setSupportActionBar(view.social_register_toolbar)
        (activity as LoginActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        return view
    }

}