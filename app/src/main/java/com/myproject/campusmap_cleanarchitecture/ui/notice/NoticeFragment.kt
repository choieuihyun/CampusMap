package com.myproject.campusmap_cleanarchitecture.ui.notice

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.myproject.campusmap_cleanarchitecture.R
import com.myproject.campusmap_cleanarchitecture.databinding.NoticeFragmentBinding
import com.myproject.campusmap_cleanarchitecture.ui.BaseFragment
import com.myproject.campusmap_cleanarchitecture.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoticeFragment : BaseFragment<NoticeFragmentBinding>(R.layout.notice_fragment) {

    private lateinit var mainActivity: MainActivity
    private lateinit var navController: NavController

    private val callbacks : ItemClickListener? = null

    interface ItemClickListener {
        fun bottomNaviClick(id : Int)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

/*        if(savedInstanceState == null) { // 앱 처음 켰을때만.
            binding.bottomNavigationView.selectedItemId = R.id.noticeGeneralFragment
        }*/


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //setupNavigation()

/*        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.noticeGeneralFragment -> callbacks?.bottomNaviClick(R.id.noticeGeneralFragment)
                R.id.noticeHaksaFragment -> callbacks?.bottomNaviClick(R.id.noticeHaksaFragment)
                R.id.noticeRecruitFragment -> callbacks?.bottomNaviClick(R.id.noticeRecruitFragment)
                R.id.noticeJanghakFragment -> callbacks?.bottomNaviClick(R.id.noticeJanghakFragment)
            }
            true
        }*/


    }

    // 그래프를 두개 만들어야하냐? 호스트는 못바꾸는거 같은데
/*    private fun setupNavigation() { // 어떻게 설정해야할지를 모르겠어서 그냥 Fragment 넘기는 방식 채택.

        val host = mainActivity.supportFragmentManager
            .findFragmentById(R.id.bn_nav_host_fragment) as NavHostFragment? ?: return

        navController = host.navController
        binding.bottomNavigationView.setupWithNavController(navController)

    }*/

}