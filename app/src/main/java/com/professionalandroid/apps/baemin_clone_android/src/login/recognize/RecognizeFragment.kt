package com.professionalandroid.apps.baemin_clone_android.src.login.recognize

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.professionalandroid.apps.baemin_clone_android.PhoneNumber
import com.professionalandroid.apps.baemin_clone_android.PhoneRecognization
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.src.login.LoginActivity
import com.professionalandroid.apps.baemin_clone_android.src.login.recognize.interfaces.RecognizeFragmentView
import com.professionalandroid.apps.baemin_clone_android.src.login.recognize.models.SMSResponse
import com.professionalandroid.apps.baemin_clone_android.src.login.register.RegisterFragment
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity
import kotlinx.android.synthetic.main.fragment_recognize.*
import kotlinx.android.synthetic.main.fragment_recognize.view.*
import kotlinx.android.synthetic.main.fragment_register.*

class RecognizeFragment : Fragment(), RecognizeFragmentView {

    val mRecognizeService = RecognizeService(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_recognize, container, false)


        view.recognize_submit_btn.setOnClickListener {
            val phoneNumber = PhoneNumber(recognize_phone.text.toString())
            mRecognizeService.authSMS(phoneNumber)
        }

        view.recognize_confirm.setOnClickListener {
            val data = PhoneRecognization(recognize_number.text.toString(), recognize_phone.text.toString())
            mRecognizeService.authNumber(data)
        }

        return view
    }

    override fun authSMS() {
        recognize_submit_btn.visibility = View.GONE
        recognize_num.visibility = View.VISIBLE
        recognize_number.visibility = View.VISIBLE
        recognize_reset.visibility = View.VISIBLE
        recognize_confirm.setTextColor(Color.BLACK)
        recognize_phone.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.email_check, 0);

    }

    override fun authNumber() {
        val registerPage = RegisterFragment()
        (activity as LoginActivity).addFragment(registerPage)
    }


}