package com.professionalandroid.apps.baemin_clone_android.src.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.src.login.register.RegisterFragment
import com.professionalandroid.apps.baemin_clone_android.src.login.socialregister.SocialRegisterFragment
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

        (activity as LoginActivity).setSupportActionBar(view.agreement_toolbar)
        (activity as LoginActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if(arguments!= null) {
            val kinds = arguments?.getString("kinds")

            view.agreement_checkBox.setOnClickListener {
                if (it.agreement_checkBox.isChecked) {
                    view.agreement_next_btn.apply {
                        setBackgroundResource(R.drawable.round_button2)
                        setOnClickListener {
                            val registerPage =
                                RegisterFragment()
                            val socialregisterPage =
                                SocialRegisterFragment()
                            when(kinds) {
                                "general" -> (activity as LoginActivity).addFragment(registerPage)
                                "naver" -> (activity as LoginActivity).addFragment(socialregisterPage.apply {
                                        arguments = Bundle().apply {
                                            putString("token", arguments?.getString("token"))
                                        }
                                    })

                            }
                        }
                    }
                } else {
                    view.agreement_next_btn.apply {
                        setBackgroundResource(R.drawable.round_button)
                        setOnClickListener {

                        }
                    }
                }
            }
        }
        return view
    }

    // backbtn on tabbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        (activity as LoginActivity).closeFragemtn(this)


        return super.onOptionsItemSelected(item)
    }
}