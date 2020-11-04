package com.professionalandroid.apps.baemin_clone_android.src.login.register

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.professionalandroid.apps.baemin_clone_android.NewUserInfo
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.email
import com.professionalandroid.apps.baemin_clone_android.src.login.LoginActivity
import com.professionalandroid.apps.baemin_clone_android.src.login.register.interfaces.RegisterFragmentView
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity.Companion.login_status
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity.Companion.user_status
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.view.*

class RegisterFragment : Fragment(), RegisterFragmentView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_register, container, false)
        (activity as LoginActivity).setSupportActionBar(view.register_toolbar)
        (activity as LoginActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val registerService = RegisterService(this)

        view.register_check_duplicate_btn.setOnClickListener {
            val email =
                email(view.register_email.text.toString())
            registerService.checkDuplication(email)
        }

        view.register_submit_btn.setOnClickListener {
            val newUser_data =
                NewUserInfo(
                    register_email.text.toString(),
                    register_password.text.toString(),
                    register_nicName.text.toString(),
                    register_birth.text.toString()
                )
            registerService.registerNewId(newUser_data)
        }

        return view
    }

    override fun availableEmail() {
        register_input.visibility = View.VISIBLE
        Log.d("test", "visible")
    }

    override fun successRegister() {
        user_status = true
        login_status = true
        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)
    }

    override fun saveJwt(jwt: String) {
        val ss = (activity as LoginActivity).getSharedPreferences("sSharedPreferences", Context.MODE_PRIVATE)
        ss.edit().apply{
            putString("TAG", jwt)
            apply()
        }
    }

}