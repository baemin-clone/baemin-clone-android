package com.professionalandroid.apps.baemin_clone_android.src.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.src.login.recognize.RecognizeFragment
import com.professionalandroid.apps.baemin_clone_android.src.login.register.RegisterFragment
import com.professionalandroid.apps.baemin_clone_android.src.login.socialregister.SocialRegisterFragment
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity
import kotlinx.android.synthetic.main.fragment_agreement.*
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
        (activity as LoginActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        (activity as LoginActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val kinds = arguments?.getString("kinds")
        val token = arguments?.getString("token", "")



        view.agreement_checkBox.setOnClickListener {
            if(agreement_checkBox.isChecked) {
                agreement_checkBox1.isChecked = true
                agreement_checkBox2.isChecked = true
                agreement_checkBox3.isChecked = true
                agreement_checkBox4.isChecked = true
                agreement_checkBox5.isChecked = true
                agreement_checkBox6.isChecked = true
            }
            else{
                agreement_checkBox1.isChecked = false
                agreement_checkBox2.isChecked = false
                agreement_checkBox3.isChecked = false
                agreement_checkBox4.isChecked = false
                agreement_checkBox5.isChecked = false
                agreement_checkBox6.isChecked = false
            }
            check(kinds!!, token!!)
        }

        view.agreement_checkBox1.setOnClickListener { check(kinds!!, token!!) }
        view.agreement_checkBox2.setOnClickListener { check(kinds!!, token!!) }
        view.agreement_checkBox3.setOnClickListener { check(kinds!!, token!!) }
        view.agreement_checkBox4.setOnClickListener { check(kinds!!, token!!) }
        view.agreement_checkBox5.setOnClickListener { check(kinds!!, token!!) }
        view.agreement_checkBox6.setOnClickListener { check(kinds!!, token!!) }

        return view
    }

    // backbtn on tabbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        (activity as LoginActivity).closeFragemtn(this)

        return super.onOptionsItemSelected(item)
    }

    // check if user agrees or not
    fun check(kinds: String, token: String){
        if(agreement_checkBox1.isChecked
            && agreement_checkBox2.isChecked
            && agreement_checkBox3.isChecked
            && agreement_checkBox4.isChecked
            && agreement_checkBox5.isChecked
            && agreement_checkBox6.isChecked
        ){
            agreement_checkBox.isChecked = true
            agreement_next_btn.apply {
                setBackgroundResource(R.drawable.round_button2)
                setOnClickListener {
                    val recognizePage =
                        RecognizeFragment()
                    val socialregisterPage =
                        SocialRegisterFragment().apply {
                            arguments = Bundle().apply {
                                putString("token", token)
                            }
                        }
                    when(kinds) {
                        "general" -> (activity as LoginActivity).addFragment(recognizePage)
                        "naver" -> (activity as LoginActivity).addFragment(socialregisterPage)
                    }
                }
            }
        }
        else if(agreement_checkBox1.isChecked
            && agreement_checkBox2.isChecked
            && agreement_checkBox3.isChecked
            && agreement_checkBox6.isChecked
        ) {
            agreement_checkBox.isChecked = false
            agreement_next_btn.apply {
                setBackgroundResource(R.drawable.round_button2)
                setOnClickListener {
                    val recognizePage =
                        RecognizeFragment()
                    val socialregisterPage =
                        SocialRegisterFragment().apply {
                            arguments = Bundle().apply {
                                putString("token", token)
                            }
                        }
                    when(kinds) {
                        "general" -> (activity as LoginActivity).addFragment(recognizePage)
                        "naver" -> (activity as LoginActivity).addFragment(socialregisterPage)
                    }
                }
            }
        }
        else{
            agreement_checkBox.isChecked = false
            agreement_next_btn.apply {
                setBackgroundResource(R.drawable.round_button)
                setOnClickListener {

                }
            }
        }
    }
}