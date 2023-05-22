package com.myproject.campusmap_cleanarchitecture.domain.repository

import com.myproject.campusmap_cleanarchitecture.domain.model.NoticeItem

interface NoticeRepository {

    suspend fun getNoticeData(url: String, linkUrl: String): ArrayList<NoticeItem>

}