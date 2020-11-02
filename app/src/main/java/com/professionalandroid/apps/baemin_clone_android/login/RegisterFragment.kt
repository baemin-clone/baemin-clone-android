package com.professionalandroid.apps.baemin_clone_android.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.professionalandroid.apps.baemin_clone_android.MainActivity
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.retrofit.*
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterFragment : Fragment() {

    lateinit var connect_server: RetrofitService
    override fun onAttach(context: Context) {
        super.onAttach(context)
        connect_server = ConnectRetrofit(context).retrofitService()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_register, container, false)
        (activity as LoginPage).setSupportActionBar(view.register_toolbar)
        (activity as LoginPage).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // 아이디 중복여부코드를 담을 변수
        var isRegistered = 0

        view.register_check_duplicate_btn.setOnClickListener {
            val email_data = email(view.register_email.text.toString())

            connect_server.checkDuplicateEmail(email_data).enqueue(object : Callback<email_response>{
                override fun onFailure(call: Call<email_response>, t: Throwable) {
                    Log.d("test","실패")
                }

                override fun onResponse(
                    call: Call<email_response>,
                    response: Response<email_response>
                ) {
                    val answer = response.body()
                    Log.d("test", "$answer")
                    if(answer != null){
                        isRegistered = answer.code
                        if (isRegistered == 2){
                            view.register_input.visibility = View.VISIBLE
                            Log.d("test", "visible")
                        }
                    }
                }

            })

            view.register_submit_btn.setOnClickListener {
                if(isRegistered == 2 && register_nicName.text.isNotEmpty() && register_password.text.isNotEmpty() && register_birth.text.isNotEmpty() ){
                    val newUser_data = NewUserInfo(register_email.text.toString(), register_password.text.toString(), register_nicName.text.toString(), register_birth.text.toString())
                    connect_server.registerNewId(newUser_data).enqueue(object : Callback<NewUserInfo_response>{
                        override fun onFailure(call: Call<NewUserInfo_response>, t: Throwable) {
                            Log.d("test", "실패")
                        }

                        override fun onResponse(
                            call: Call<NewUserInfo_response>,
                            response: Response<NewUserInfo_response>
                        ) {
                            Log.d("test", response.body()!!.toString())
                            val body = response.body()
                            if(body?.code == 1){
                                val intent = Intent(context, MainActivity::class.java)
                                startActivity(intent)
                            }
                        }

                    })
                }
            }

        }

        view.register_submit_btn.setOnClickListener {
            Log.d("test", view.register_birth.text.toString())
        }




        return view
    }

}