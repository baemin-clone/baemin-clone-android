package com.professionalandroid.apps.baemin_clone_android.src.myinfo.modifyMyInfo

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.professionalandroid.apps.baemin_clone_android.InfoChange
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.src.ApplicationClass.Companion.X_ACCESS_TOKEN
import com.professionalandroid.apps.baemin_clone_android.src.ApplicationClass.Companion.sSharedPreferences
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity.Companion.login_status
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity.Companion.request_Image_File_list
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity.Companion.request_Image_list
import com.professionalandroid.apps.baemin_clone_android.src.main.MainActivity.Companion.user_status
import com.professionalandroid.apps.baemin_clone_android.src.myinfo.modifyMyInfo.interfaces.ModifyMyinfoFragmentView
import kotlinx.android.synthetic.main.fragment_modify_myinfo.*
import kotlinx.android.synthetic.main.fragment_modify_myinfo.view.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class ModifyMyinfoFragment() : Fragment(), ModifyMyinfoFragmentView , CustomPopupDialog.CustomDialogClickListener{

    lateinit var customPopupDialog: CustomPopupDialog
    lateinit var mlistener: Logout

    override fun onAttach(context: Context) {
        super.onAttach(context)
        customPopupDialog = CustomPopupDialog(context, this)
    }

    interface Logout{
        fun logout()
    }

    constructor(listener: Logout):this(){
        this.mlistener = listener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_modify_myinfo, container, false)
        (activity as MainActivity).setSupportActionBar(view.modify_myinfo_toolbar)
        (activity as MainActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val modifyMyinfoService = ModifyMyinfoService(this)

        modifyMyinfoService.getInfo()


        view.modify_myinfo_withdrawal.setOnClickListener {
            modifyMyinfoService.withdrawal()
        }

        view.modify_myinfo_logout.setOnClickListener {
            user_status = false
            login_status = true
            deletejwt()
        }

        request_Image_File_list.clear()
        val uri = Uri.parse("android.resource://com.professionalandroid.apps.capston_meeting/drawable/happy.jpg")
        request_Image_File_list.add(uri)
        request_Image_list.clear()
        request_Image_list.add(view.modify_myinfo_profile_img)

        view.modify_myinfo_profile_img.apply {
            setOnClickListener {
                MainActivity.img_num = 0
                customPopupDialog.show()
            }
        }

        view.modify_myinfo_submit_btn.setOnClickListener {
            val originalFile1 = File(request_Image_File_list[0].path!!)

            val filePart1: RequestBody = RequestBody.create(
                MediaType.parse("image/*"),
                originalFile1
            )

            val file1: MultipartBody.Part =
                MultipartBody.Part.createFormData("img", originalFile1.name, filePart1)

            val data = InfoChange(modify_myinfo_nickname.text.toString(), modify_myinfo_pwd.text.toString())
            modifyMyinfoService.change(data)
            modifyMyinfoService.savephoto(file1)
            (activity as MainActivity).closeFragment(this)
        }
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity as MainActivity).showBottomeNav()

    }

    // backbtn on tab bar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d("test", "dd")
        when (item.itemId) {
            android.R.id.home -> {
                (activity as MainActivity).closeFragment(this)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun deletejwt() {
        sSharedPreferences!!.edit().apply{
            remove(X_ACCESS_TOKEN)
            apply()
        }

        mlistener.logout()
        (activity as MainActivity).closeFragment(this)

    }

    override fun putUserdata(nickname: String?, profileImg:String, email: String?, phone: String?) {
        modify_myinfo_nickname.setText(nickname)
        modify_myinfo_email.text = email
        Glide.with(this)
            .load(profileImg)
            .centerCrop()
            .into(modify_myinfo_profile_img)
        modify_myinfo_phone1.text = phone?.slice(IntRange(0,2))
        modify_myinfo_phone2.text = phone?.slice(IntRange(4,7))
        modify_myinfo_phone3.text = phone?.slice(IntRange(9,12))
    }

    override fun clickCamera() {
        (activity as MainActivity).takeCapture()
    }

    override fun clickGallery() {
        (activity as MainActivity).getPhotoFromMyGallary()
    }



}