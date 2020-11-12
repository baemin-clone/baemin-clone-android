package com.professionalandroid.apps.baemin_clone_android.src.main

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentResolver
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.location.LocationManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.view.View
import android.webkit.MimeTypeMap
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.professionalandroid.apps.baemin_clone_android.src.history.HistoryFragment
import com.professionalandroid.apps.baemin_clone_android.R
import com.professionalandroid.apps.baemin_clone_android.RecommendFragment
import com.professionalandroid.apps.baemin_clone_android.src.ApplicationClass.Companion.X_ACCESS_TOKEN
import com.professionalandroid.apps.baemin_clone_android.src.ApplicationClass.Companion.sSharedPreferences
import com.professionalandroid.apps.baemin_clone_android.src.GpsTracker
import com.professionalandroid.apps.baemin_clone_android.src.bookmark.BookmarkFragment
import com.professionalandroid.apps.baemin_clone_android.src.history.OrderHistoryFragment
import com.professionalandroid.apps.baemin_clone_android.src.homeFragment.HomeFragment
import com.professionalandroid.apps.baemin_clone_android.src.myinfoFragment.MyinfoFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    companion object{
        var user_status = false //
        var login_status = false    // if user just login, true
        var user_nickname: String? = "근짱"
        var user_address: String? = "서울 금천구 가산동 가산디지털1로 128 "

        const val TAG_LIST_HOMEPAGE = "TAG_LIST_HOMEPAGE"
        private val REQUEST_IMAGE_CAPTURE = 1
        lateinit var currentPhotoPath : String //문자열 형태의 사진 경로값 (초기값을 null로 시작하고 싶을 때 - lateinti var)
        val REQUEST_IMAGE_PICK = 10
        val request_Image_list = mutableListOf<ImageView>()
        val request_Image_File_list = mutableListOf<Uri>()
        var img_num = 0
    }

    private val gpsTracker: GpsTracker? = null

    private val GPS_ENABLE_REQUEST_CODE = 2001
    private val PERMISSIONS_REQUEST_CODE = 100
    var REQUIRED_PERMISSIONS = arrayOf<String>(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.CAMERA,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    val ft = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // recover theme
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_main)

        if (checkLocationServicesStatus()) {
            checkRunTimePermission();
        } else {
            showDialogForLocationServiceSetting();
        }

        Log.d("test", sSharedPreferences?.getString(X_ACCESS_TOKEN, "noToken")!!)
        // ft.addOnBackStackChangedListener(getListener())

        val homePage =
            HomeFragment()
        val recommendPage =
            RecommendFragment()
        val bookmarkPage =
            BookmarkFragment()
        val historyPage =
            OrderHistoryFragment()
        val myinfoPage =
            MyinfoFragment()


        // 자동로그인의 경우 정보를 shared preference에 저장해놓고 변경
        if (login_status){   // user 가 지금 로그인 했을 때
            ft.beginTransaction().replace(R.id.main_layout, myinfoPage).commit()
            bottom_navigation_bar.selectedItemId = R.id.navigation_bar_myinfo
        }
        else{
            ft.beginTransaction().replace(R.id.main_layout, homePage).commit()

        }
        bottom_navigation_bar.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_bar_home -> ft.beginTransaction().replace(
                    R.id.main_layout, homePage).commit()
                R.id.navigation_bar_recommend -> ft.beginTransaction().replace(
                    R.id.main_layout, recommendPage).commit()
                R.id.navigation_bar_bookmark -> ft.beginTransaction().replace(
                    R.id.main_layout, bookmarkPage).commit()
                R.id.navigation_bar_history -> ft.beginTransaction().replace(
                    R.id.main_layout, historyPage).commit()
                R.id.navigation_bar_myinfo -> ft.beginTransaction().replace(
                    R.id.main_layout, myinfoPage).commit()
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    fun addFragment(fragment: Fragment){
        val transaction = ft.beginTransaction()
        transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
        transaction.add(R.id.main_full_layout, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
        bottom_navigation_bar.visibility = View.GONE
    }

    fun closeFragment(fragment: Fragment){
        val manager = ft
        val transaction = manager.beginTransaction()
        transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
        transaction.remove(fragment)
        transaction.commit()
        manager.popBackStack()
    }

    fun showBottomeNav(){
        bottom_navigation_bar.visibility = View.VISIBLE
    }


    /*
     * ActivityCompat.requestPermissions를 사용한 퍼미션 요청의 결과를 리턴받는 메소드입니다.
     */
    override fun onRequestPermissionsResult(
        permsRequestCode: Int,
        permissions: Array<String?>,
        grandResults: IntArray
    ) {
        if (permsRequestCode == PERMISSIONS_REQUEST_CODE && grandResults.size == REQUIRED_PERMISSIONS.size) {

            // 요청 코드가 PERMISSIONS_REQUEST_CODE 이고, 요청한 퍼미션 개수만큼 수신되었다면
            var check_result = true


            // 모든 퍼미션을 허용했는지 체크합니다.
            for (result in grandResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    check_result = false
                    break
                }
            }
            if (check_result) {

                //위치 값을 가져올 수 있음
            } else {
                // 거부한 퍼미션이 있다면 앱을 사용할 수 없는 이유를 설명해주고 앱을 종료합니다.2 가지 경우가 있습니다.
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        REQUIRED_PERMISSIONS[0]
                    )
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        REQUIRED_PERMISSIONS[1]
                    )
                ) {
                    Toast.makeText(
                        this,
                        "퍼미션이 거부되었습니다. 앱을 다시 실행하여 퍼미션을 허용해주세요.",
                        Toast.LENGTH_LONG
                    ).show()
                    finish()
                } else {
                    Toast.makeText(
                        this,
                        "퍼미션이 거부되었습니다. 설정(앱 정보)에서 퍼미션을 허용해야 합니다. ",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    fun checkRunTimePermission() {

        //런타임 퍼미션 처리
        // 1. 위치 퍼미션을 가지고 있는지 체크합니다.
        val hasFineLocationPermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        val hasCoarseLocationPermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        val hasCameraPermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        )
        val hasWriteExternalStoragePermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        val hasReadExternalStragePermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
            hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED &&
            hasCameraPermission == PackageManager.PERMISSION_GRANTED &&
            hasWriteExternalStoragePermission == PackageManager.PERMISSION_GRANTED&&
            hasReadExternalStragePermission == PackageManager.PERMISSION_GRANTED
        ) {

            // 2. 이미 퍼미션을 가지고 있다면
            // ( 안드로이드 6.0 이하 버전은 런타임 퍼미션이 필요없기 때문에 이미 허용된 걸로 인식합니다.)


            // 3.  위치 값을 가져올 수 있음
        } else {  //2. 퍼미션 요청을 허용한 적이 없다면 퍼미션 요청이 필요합니다. 2가지 경우(3-1, 4-1)가 있습니다.

            // 3-1. 사용자가 퍼미션 거부를 한 적이 있는 경우에는
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    REQUIRED_PERMISSIONS[0]
                )
            ) {

                // 3-2. 요청을 진행하기 전에 사용자가에게 퍼미션이 필요한 이유를 설명해줄 필요가 있습니다.
                Toast.makeText(this, "이 앱을 실행하려면 위치 접근 권한이 필요합니다.", Toast.LENGTH_LONG)
                    .show()
                // 3-3. 사용자게에 퍼미션 요청을 합니다. 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(
                    this, REQUIRED_PERMISSIONS,
                    PERMISSIONS_REQUEST_CODE
                )
            } else {
                // 4-1. 사용자가 퍼미션 거부를 한 적이 없는 경우에는 퍼미션 요청을 바로 합니다.
                // 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(
                    this, REQUIRED_PERMISSIONS,
                    PERMISSIONS_REQUEST_CODE
                )
            }
        }
    }

    //여기부터는 GPS 활성화를 위한 메소드들
    private fun showDialogForLocationServiceSetting() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("위치 서비스 비활성화")
        builder.setMessage(
            """
                앱을 사용하기 위해서는 위치 서비스가 필요합니다.
                위치 설정을 수정하실래요?
                """.trimIndent()
        )
        builder.setCancelable(true)
        builder.setPositiveButton("설정", DialogInterface.OnClickListener { dialog, id ->
            val callGPSSettingIntent =
                Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE)
        })
        builder.setNegativeButton("취소",
            DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })
        builder.create().show()
    }

    fun checkLocationServicesStatus(): Boolean {
        val locationManager =
            getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
    }


    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            GPS_ENABLE_REQUEST_CODE ->
                //사용자가 GPS 활성 시켰는지 검사
                if (checkLocationServicesStatus()) {
                    if (checkLocationServicesStatus()) {
                        Log.d("@@@", "onActivityResult : GPS 활성화 되있음")
                        checkRunTimePermission()
                        return
                    }
                }
        }

        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK){
            val bitmap : Bitmap
            val file = File(currentPhotoPath)
            if(Build.VERSION.SDK_INT < 28){//안드로이드 9.0 보다 버전이 낮을 경우
                bitmap = MediaStore.Images.Media.getBitmap(contentResolver, Uri.fromFile(file))
                request_Image_list[img_num].setImageBitmap(bitmap)
                request_Image_File_list[img_num] = Uri.fromFile(file)
                Log.d("test", Uri.fromFile(file).toString())

            }else{//안드로이드 9.0 보다 버전이 높을 경우
                val decode = ImageDecoder.createSource(
                    this.contentResolver,
                    Uri.fromFile(file)
                )
                bitmap = ImageDecoder.decodeBitmap(decode)
                request_Image_list[img_num].setImageBitmap(bitmap)
                request_Image_File_list[img_num] = Uri.fromFile(file)
                Log.d("test", Uri.fromFile(file).toString())
            }
        }

        if(requestCode == REQUEST_IMAGE_PICK && resultCode == Activity.RESULT_OK){
            val bitmap : Bitmap
            if(Build.VERSION.SDK_INT < 28){//안드로이드 9.0 보다 버전이 낮을 경우
                bitmap = MediaStore.Images.Media.getBitmap(contentResolver,data?.data)
                request_Image_File_list[img_num] = Uri.parse("file://"+ getPath(data?.data!!) )
                Log.d("test", "android 9.0 미만 ${request_Image_File_list[img_num]}")
                request_Image_list[img_num].setImageBitmap(bitmap)
            }
            else if(Build.VERSION.SDK_INT >= 29) {//안드로이드 10.0 보다 버전이 높은 경우
                val decode = ImageDecoder.createSource(
                    this.contentResolver,
                    data?.data!!
                )
                bitmap = ImageDecoder.decodeBitmap(decode)
                request_Image_File_list[img_num] =  Uri.fromFile(createCopy(this, data.data!!))
                request_Image_list[img_num].setImageBitmap(bitmap)
            }
            else{//안드로이드 9.0 인 경우
                val decode = ImageDecoder.createSource(
                    this.contentResolver,
                    data?.data!!
                )
                bitmap = ImageDecoder.decodeBitmap(decode)
                request_Image_File_list[img_num] =  Uri.parse("file://" + getPath(data.data!!))
                Log.d("안드로이드 9.0 test", "${request_Image_File_list[img_num]}")

                request_Image_list[img_num].setImageBitmap(bitmap)
            }
        }

    }

    fun getPhotoFromMyGallary() {
        Intent(Intent.ACTION_PICK).apply{
            type = "image/*"
            startActivityForResult(this,REQUEST_IMAGE_PICK)
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    fun takeCapture() {
        //기본 카메라 앱 실행
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                val photoFile : File? = try{
                    createImageFile()
                }catch (e:Exception){
                    null
                }
                photoFile?.also {
                    val photoURI : Uri = FileProvider.getUriForFile(
                        this,
                        "com.professionalandroid.apps.baemin_clone_android.provider",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }
    }

    private fun createImageFile(): File {
        val timestamp : String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir : File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("JPEG_${timestamp}_",".jpeg",storageDir).apply {
            currentPhotoPath = absolutePath
        }
    }

    fun getPath(uri: Uri): String?{
        val result: String?
        val cursor: Cursor? = contentResolver.query(uri, null, null, null, null)

        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = uri.path
        } else {
            cursor.moveToFirst()
            val idx: Int = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            result = cursor.getString(idx)
            cursor.close()
        }

        return result
    }

    @Nullable
    fun createCopy(
        @NonNull context: Context, @NonNull uri: Uri
    ): File? {
        val imageUrl = getPath(uri)
        val mimeTypeMap = MimeTypeMap.getSingleton()
        val extension = MimeTypeMap.getFileExtensionFromUrl(imageUrl)
        val mimeType = mimeTypeMap.getMimeTypeFromExtension(extension)

        val mimetype = "." + StringBuffer(mimeType!!).substring(6).toString()
        Log.d("test", mimetype)
        val storageDir : File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val contentResolver: ContentResolver = context.contentResolver ?: return null
        // Create temporary
        val file = File.createTempFile( "${System.currentTimeMillis()}",mimetype,storageDir).apply {
            currentPhotoPath = absolutePath
        }
        try {
            val inputStream: InputStream = contentResolver.openInputStream(uri) ?: return null
            val outputStream: OutputStream = FileOutputStream(file)
            val buf = ByteArray(1024)
            var len: Int
            while (inputStream.read(buf).also { len = it } > 0) outputStream.write(buf, 0, len)
            outputStream.close()
            inputStream.close()
        } catch (ignore: IOException) {
            return null
        }
        return file
    }

}