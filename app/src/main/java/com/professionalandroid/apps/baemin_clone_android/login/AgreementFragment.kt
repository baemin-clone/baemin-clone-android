package com.professionalandroid.apps.baemin_clone_android.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

        (activity as LoginPage).setSupportActionBar(view.agreement_toolbar)
        (activity as LoginPage).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        view.agreement_checkBox.setOnClickListener {
            if(it.agreement_checkBox.isChecked){
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
        }
        return view
    }

    // toolbar의 backbutton 정으
    override fun onOptionsItemSelected(item: MenuItem): Boolean {



        return super.onOptionsItemSelected(item)
    }
}