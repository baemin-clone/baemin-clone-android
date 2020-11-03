package com.professionalandroid.apps.baemin_clone_android.src.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.professionalandroid.apps.baemin_clone_android.*
import com.professionalandroid.apps.baemin_clone_android.src.homeFragment.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object{
        var user_status = false
        var login_status = false
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homePage =
            HomeFragment()
        val recommendPage =
            RecommendFragment()
        val bookmarkPage =
            BookmarkFragment()
        val historyPage =
            HistoryFragment()
        val myinfoPage =
            MyinfoFragment()

        val ft = supportFragmentManager

        // 자동로그인의 경우 정보를 shared preference에 저장해놓고 변경
        if (user_status && login_status){   // user 가 지금 로그인 했을 때
            ft.beginTransaction().replace(R.id.main_layout, myinfoPage).commit()
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

}