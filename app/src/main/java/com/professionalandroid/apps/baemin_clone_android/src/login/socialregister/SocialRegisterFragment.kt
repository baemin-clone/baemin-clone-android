package com.professionalandroid.apps.baemin_clone_android.src.login.socialregister

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.professionalandroid.apps.baemin_clone_android.NewSocialUserInfo
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.src.ApplicationClass
import com.professionalandroid.apps.baemin_clone_android.src.ApplicationClass.Companion.sSharedPreferences
import com.professionalandroid.apps.baemin_clone_android.src.login.LoginActivity
import com.professionalandroid.apps.baemin_clone_android.src.login.socialregister.interfaces.SocialRegisterFragmentView
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity.Companion.login_status
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity.Companion.user_status
import kotlinx.android.synthetic.main.fragment_social_register.view.*

class SocialRegisterFragment : Fragment(), SocialRegisterFragmentView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_social_register, container, false)

        val socialRegisterService = SocialRegisterService(this)

        (activity as LoginActivity).setSupportActionBar(view.social_register_toolbar)
        (activity as LoginActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if(arguments?.getString("token")!= null) {
            val data = NewSocialUserInfo(
                view.social_register_nicName.text.toString(),
                view.social_register_birth.text.toString(),
                arguments?.getString("token")!!
            )
            socialRegisterService.registerNewSocialId(data)
        }
        return view
    }

    override fun savejwt(jwt: String) {
        sSharedPreferences!!.edit().apply{
            putString(ApplicationClass.X_ACCESS_TOKEN, jwt)
            apply()
        }
    }

    override fun successRegister() {
        user_status = true
        login_status = true
        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)
        (activity as LoginActivity).finishAffinity()
    }

}