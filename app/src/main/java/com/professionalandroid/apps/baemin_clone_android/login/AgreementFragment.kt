package com.professionalandroid.apps.baemin_clone_android.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.professionalandroid.apps.baemin_clone_android.MainActivity
import com.professionalandroid.apps.baemin_clone_android.R
import kotlinx.android.synthetic.main.fragment_agreement.view.*

class AgreementFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_agreement, container, false)

        if(view.checkBox.isChecked){
            view.agreement_next_btn.apply {
                setBackgroundResource(R.drawable.round_button2)
                setOnClickListener {
                    val registerPage = RegisterFragment()
                    (activity as LoginPage).addFragment(registerPage)
                }
            }
        }
        else{
            view.agreement_next_btn.apply {
                setBackgroundResource(R.drawable.round_button)
                setOnClickListener {

                }
            }
        }

        return view
    }


}