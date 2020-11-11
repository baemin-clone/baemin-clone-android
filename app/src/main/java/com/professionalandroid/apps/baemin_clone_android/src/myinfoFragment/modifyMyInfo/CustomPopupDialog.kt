package com.professionalandroid.apps.baemin_clone_android.src.myinfoFragment.modifyMyInfo

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.professionalandroid.apps.baemin_clone_android.R
import kotlinx.android.synthetic.main.layout_custom_dialog.*


class CustomPopupDialog(context: Context, customDialogClickListener: CustomDialogClickListener) : Dialog(context) {

    interface CustomDialogClickListener{
        fun clickCamera()
        fun clickGallery()
    }

    private var mcontext: Context
    private val customDialogClickListener: CustomDialogClickListener


    init {
        this.mcontext = context
        this.customDialogClickListener = customDialogClickListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_custom_dialog)

        camera.setOnClickListener {
            this.customDialogClickListener.clickCamera()
            dismiss()
        }
        gallery.setOnClickListener {
            this.customDialogClickListener.clickGallery()
            dismiss()
        }
        close.setOnClickListener {
            dismiss()
        }

    }


}