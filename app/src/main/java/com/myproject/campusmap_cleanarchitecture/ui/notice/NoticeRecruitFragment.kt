package com.myproject.campusmap_cleanarchitecture.ui.notice

import android.os.Bundle
import com.myproject.campusmap_cleanarchitecture.R
import com.myproject.campusmap_cleanarchitecture.databinding.NoticeFragmentRecruitBinding
import com.myproject.campusmap_cleanarchitecture.ui.BaseFragment
import timber.log.Timber

class NoticeRecruitFragment : BaseFragment<NoticeFragmentRecruitBinding>(R.layout.notice_fragment_recruit) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.tag("noticeTest").d("recruit")

    }

    companion object {
        fun newInstance() : NoticeRecruitFragment {
            return NoticeRecruitFragment()
        }
    }

}