package com.myproject.campusmap_cleanarchitecture.presentation.notice

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.myproject.campusmap_cleanarchitecture.presentation.BaseFragment
import com.myproject.campusmap_cleanarchitecture.presentation.R
import com.myproject.campusmap_cleanarchitecture.presentation.databinding.NoticeFragmentBinding
import com.myproject.campusmap_cleanarchitecture.presentation.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoticeFragment : BaseFragment<NoticeFragmentBinding>(R.layout.notice_fragment) {

    private lateinit var mainActivity: MainActivity


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainActivity.getBinding.bottomNavigationView.isVisible = true

    }
}