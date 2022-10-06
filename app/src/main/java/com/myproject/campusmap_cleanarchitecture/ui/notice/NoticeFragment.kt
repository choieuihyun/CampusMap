package com.campusmap.android.campusmap_with_kakao.notice

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.campusmap.android.campusmap_with_kakao.MainActivity
import com.campusmap.android.campusmap_with_kakao.R
import com.campusmap.android.campusmap_with_kakao.databinding.NoticeFragmentBinding
import com.campusmap.android.campusmap_with_kakao.jsoup.NoticeItem

private const val TAG = "NoticeFragment"

class NoticeFragment : Fragment() {

    private lateinit var binding: NoticeFragmentBinding
    private lateinit var noticeArray: ArrayList<NoticeItem>
    private lateinit var dialog: Dialog
    private lateinit var mainActivity: MainActivity

    private lateinit var callbacks: Callbacks

    interface Callbacks {
        fun onNoticeClicked(link: String)
        fun bottomNaviClicked(id : Int)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "attach")
        mainActivity = context as MainActivity
        callbacks = mainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "creat")
        // 앱 킨상태에서 화면 뒤로갔다가 다시 들어오면 그대로 공지사항 데이터들이 떠있으면 좋을것 같은데
        // ViewModel로 데이터 보존해줘야하냐? 약간 그런 느낌인데
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "createView")
        binding = DataBindingUtil.inflate(inflater, R.layout.notice_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "ViewCreated")

        // 이거 띄울때 findFragment? 이거 한번 다시 검색해서 써보자.
        binding.bn.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottomNavi_home -> {
                    callbacks.bottomNaviClicked(R.id.bottomNavi_home)
                }
                R.id.bottomNavi_general -> {
                    callbacks.bottomNaviClicked(R.id.bottomNavi_general)
                }
                R.id.bottomNavi_haksa -> {
                    callbacks.bottomNaviClicked(R.id.bottomNavi_haksa)
                }
                R.id.bottomNavi_recruit -> {
                    callbacks.bottomNaviClicked(R.id.bottomNavi_recruit)
                }
                R.id.bottomNavi_janghak -> {
                    callbacks.bottomNaviClicked(R.id.bottomNavi_janghak)
                }
            }
            true
        }

        // 재클릭시 안띄움. 뒷걸음질 치다가 쥐잡음.
        binding.bn.setOnItemReselectedListener { item ->
            when (item.itemId) {
                R.id.bottomNavi_home -> {
                    callbacks.bottomNaviClicked(R.id.bottomNavi_home)
                }
                R.id.bottomNavi_general -> {}
                R.id.bottomNavi_haksa -> {}
                R.id.bottomNavi_recruit -> {}
                R.id.bottomNavi_janghak -> {}
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "start")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "resume")
    }

    companion object {

        fun newInstance(): NoticeFragment {
            return NoticeFragment()
        }

    }

}