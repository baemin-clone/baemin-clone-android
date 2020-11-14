package com.professionalandroid.apps.baemin_clone_android.src.login.register

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.professionalandroid.apps.baemin_clone_android.NewUserInfo
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.email
import com.professionalandroid.apps.baemin_clone_android.src.ApplicationClass.Companion.X_ACCESS_TOKEN
import com.professionalandroid.apps.baemin_clone_android.src.ApplicationClass.Companion.sSharedPreferences
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


    val mRegisterService = RegisterService(this)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_register, container, false)
        (activity as LoginActivity).setSupportActionBar(view.register_toolbar)
        (activity as LoginActivity).supportActionBar?.setDisplayShowTitleEnabled(false);
        (activity as LoginActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        view.register_check_duplicate_btn.setOnClickListener {
            val email =
                email(view.register_email.text.toString())
            mRegisterService.checkDuplication(email)
        }

//        view.register_submit_btn.setOnClickListener {
//            val newUser_data =
//                NewUserInfo(
//                    register_email.text.toString(),
//                    register_password.text.toString(),
//                    register_nickName.text.toString(),
//                    register_birth.text.toString()
//                )
//            mRegisterService.registerNewId(newUser_data)
//        }

        view.register_nickName.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val s = p0.toString()
                if(s.length in 2 until 11){
                    register_nickName_caution.visibility = View.INVISIBLE
                }
                else{
                    register_nickName_caution.visibility = View.VISIBLE
                }
                validation()
            }

        })

        view.register_password.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val s = p0.toString()
                if(s.length > 10){
                    register_password_caution.visibility = View.INVISIBLE
                }
                else{
                    register_password_caution.visibility = View.VISIBLE
                }
                validation()
            }

        })

        view.register_birth.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val s = p0.toString()
                if(s.length == 10){
                    register_birth_caution.visibility = View.INVISIBLE
                }
                else{
                    register_birth_caution.visibility = View.VISIBLE
                }
                validation()
            }
        })

        return view
    }

    fun validation(){
        if(register_password_caution.visibility == View.INVISIBLE &&
            register_nickName_caution.visibility == View.INVISIBLE &&
            register_birth_caution.visibility == View.INVISIBLE
        ){
            register_submit_btn.apply {
                setTextColor(Color.BLACK)
                setOnClickListener {
                    val newUser_data =
                        NewUserInfo(
                            register_email.text.toString(),
                            register_password.text.toString(),
                            register_nickName.text.toString(),
                            register_birth.text.toString()
                        )
                    mRegisterService.registerNewId(newUser_data)
                }
            }
        }
        else{
            register_submit_btn.setTextColor(Color.GRAY)
        }
    }


    override fun availableEmail() {
        register_email.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.email_check, 0);
        register_input.visibility = View.VISIBLE
        Log.d("test", "visible")
    }

    override fun successRegister() {
        user_status = true
        login_status = true
        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)
        (activity as LoginActivity).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        (activity as LoginActivity).finishAffinity()
    }

    override fun saveJwt(jwt: String) {
        sSharedPreferences!!.edit().apply{
            putString(X_ACCESS_TOKEN, jwt)
            apply()
        }
    }

}