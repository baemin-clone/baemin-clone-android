package com.professionalandroid.apps.baemin_clone_android.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.professionalandroid.apps.baemin_clone_android.R
import kotlinx.android.synthetic.main.activity_login_page.*

class LoginPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        login_register.setOnClickListener {
            val agreementPage = AgreementFragment()
            addFragment(agreementPage)
        }
    }

    fun addFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.login_main_layout, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun closeFragemtn(fragment: Fragment){
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.remove(fragment)
        transaction.commit()
        manager.popBackStack()
    }
}