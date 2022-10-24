package com.myproject.campusmap_cleanarchitecture.ui.main

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.myproject.campusmap_cleanarchitecture.R
import com.myproject.campusmap_cleanarchitecture.databinding.ActivityMainBinding
import com.myproject.campusmap_cleanarchitecture.ui.map.MapFragment
import com.myproject.campusmap_cleanarchitecture.ui.notice.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(){

    private lateinit var navController: NavController
    private lateinit var splashScreen: SplashScreen

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        splashScreen = installSplashScreen()
//        startSplash()

        setContentView(binding.root)

        setupNavigation()
/*        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        if(currentFragment == null) {
            val fragment = MapFragment.newInstance()
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
        }*/

    }

    private fun setupNavigation() {
        val host = supportFragmentManager
            .findFragmentById(R.id.campusmap_nav_host_fragment) as NavHostFragment? ?: return
        navController = host.navController

    }

/*    override fun bottomNaviClick(id: Int) {
        when(id) {
            R.id.noticeGeneralFragment -> {
                createBottomNaviFragment(NoticeGeneralFragment.newInstance())
            }
            R.id.noticeHaksaFragment -> {
                createBottomNaviFragment(NoticeHaksaFragment.newInstance())
            }
            R.id.noticeRecruitFragment -> {
                createBottomNaviFragment(NoticeRecruitFragment.newInstance())
            }
            R.id.noticeJanghakFragment -> {
                createBottomNaviFragment(NoticeJanghakFragment.newInstance())
            }
        }
    }

    private fun createBottomNaviFragment(view: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.notice_container, view)
            .commit()
    }*/

/*    // splash의 애니메이션 설정
    private fun startSplash() {
        splashScreen.setOnExitAnimationListener { splashScreenView ->
            val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1f, 5f, 1f)
            val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f, 5f, 1f)

            ObjectAnimator.ofPropertyValuesHolder(splashScreenView.iconView, scaleX, scaleY).run {
                interpolator = AnticipateInterpolator()
                duration = 1000L
                doOnEnd {
                    splashScreenView.remove()
                }
                start()
            }
        }
    }*/

}