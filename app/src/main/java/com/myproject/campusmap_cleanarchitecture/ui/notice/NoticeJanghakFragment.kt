package com.myproject.campusmap_cleanarchitecture.ui.notice

import android.os.Bundle
import android.util.Log
import com.myproject.campusmap_cleanarchitecture.R
import com.myproject.campusmap_cleanarchitecture.databinding.NoticeFragmentJanghakBinding
import com.myproject.campusmap_cleanarchitecture.ui.BaseFragment
import timber.log.Timber


class NoticeJanghakFragment : BaseFragment<NoticeFragmentJanghakBinding>(R.layout.notice_fragment_janghak) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.tag("noticeTest").d("janghak")
    }

    companion object {
        fun newInstance() : NoticeJanghakFragment {
            return NoticeJanghakFragment()
        }
    }


}