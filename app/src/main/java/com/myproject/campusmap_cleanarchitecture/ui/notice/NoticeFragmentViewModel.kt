package com.myproject.campusmap_cleanarchitecture.ui.notice

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myproject.campusmap_cleanarchitecture.domain.model.NoticeItem
import com.myproject.campusmap_cleanarchitecture.domain.usecase.GetNoticeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoticeFragmentViewModel @Inject constructor(
    private val jsoupUseCase: GetNoticeUseCase
) : ViewModel() {

    private val _notices = MutableLiveData<ArrayList<NoticeItem>>()
    val notices : LiveData<ArrayList<NoticeItem>>
        get() = _notices

    fun getGeneralNotice() {
        val url = "https://www.jj.ac.kr/jj/community/notice/gennotice.jsp?mode=list&board_no=1041&pager.offset=" // 일반 공지사항 홈페이지 (1페이지)
        val linkUrl = "https://www.jj.ac.kr/jj/community/notice/gennotice.jsp" // 일반 공지사항 링크
        viewModelScope.launch {
            _notices.value = jsoupUseCase.invoke(url, linkUrl)
        }
    }

    fun getHaksaNotice() {
        val url = "https://www.jj.ac.kr/jj/community/notice/haksanotice.jsp?mode=list&board_no=1076&pager.offset=" // 학사 공지사항 홈페이지 (1페이지)
        val linkUrl = "https://www.jj.ac.kr/jj/community/notice/haksanotice.jsp" // 학사 공지사항 링크
        viewModelScope.launch {
            _notices.value = jsoupUseCase.invoke(url, linkUrl)
        }
    }

    fun getJanghakNotice() {
        val url = "https://www.jj.ac.kr/jj/community/notice/janghaknotice.jsp?mode=list&board_no=1077&pager.offset=" // 일반 공지사항 홈페이지 (1페이지)*
        val linkUrl = "https://www.jj.ac.kr/jj/community/notice/janghaknotice.jsp" // 장학 공지사항 링크
        viewModelScope.launch {
            _notices.value = jsoupUseCase.invoke(url, linkUrl)
        }
    }


    fun getRecruitNotice() {
        val url = "https://www.jj.ac.kr/jj/education/recruit/recruitinfo.jsp?mode=list&board_no=1070&pager.offset=" // 취업 공지사항 홈페이지 (1페이지)*
        val linkUrl = "https://www.jj.ac.kr/jj/education/recruit/recruitinfo.jsp" // 취업 공지사항 링크
        viewModelScope.launch {
            _notices.value = jsoupUseCase.invoke(url, linkUrl)
        }
    }


}