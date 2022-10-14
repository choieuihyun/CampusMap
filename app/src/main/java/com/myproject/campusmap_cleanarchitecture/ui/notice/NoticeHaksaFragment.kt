package com.myproject.campusmap_cleanarchitecture.ui.notice

import android.os.Bundle
import android.util.Log
import com.myproject.campusmap_cleanarchitecture.R
import com.myproject.campusmap_cleanarchitecture.databinding.NoticeFragmentHaksaBinding
import com.myproject.campusmap_cleanarchitecture.ui.BaseFragment
import timber.log.Timber


class NoticeHaksaFragment : BaseFragment<NoticeFragmentHaksaBinding>(R.layout.notice_fragment_haksa) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("noticeTest", "Haksa")

    }

    companion object {
        fun newInstance() : NoticeHaksaFragment {
            return NoticeHaksaFragment()
        }
    }


}