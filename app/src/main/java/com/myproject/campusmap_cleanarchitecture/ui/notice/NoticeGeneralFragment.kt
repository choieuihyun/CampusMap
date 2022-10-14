package com.myproject.campusmap_cleanarchitecture.ui.notice


import android.os.Bundle
import com.myproject.campusmap_cleanarchitecture.R
import com.myproject.campusmap_cleanarchitecture.databinding.NoticeFragmentGeneralBinding
import com.myproject.campusmap_cleanarchitecture.ui.BaseFragment
import timber.log.Timber


class NoticeGeneralFragment : BaseFragment<NoticeFragmentGeneralBinding>(R.layout.notice_fragment_general) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.tag("noticeTest").d("general")

    }

    companion object {
        fun newInstance() : NoticeGeneralFragment {
            return NoticeGeneralFragment()
        }
    }

}