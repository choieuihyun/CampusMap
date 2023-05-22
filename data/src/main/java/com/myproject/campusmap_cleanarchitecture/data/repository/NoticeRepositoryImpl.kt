package com.myproject.campusmap_cleanarchitecture.data.repository

import com.myproject.campusmap_cleanarchitecture.data.datasource.remotedatasource.JsoupDataSource
import com.myproject.campusmap_cleanarchitecture.data.mapper.toModel
import com.myproject.campusmap_cleanarchitecture.domain.model.NoticeItem
import com.myproject.campusmap_cleanarchitecture.domain.repository.NoticeRepository
import javax.inject.Inject

class NoticeRepositoryImpl @Inject constructor(
    private val dataSource: JsoupDataSource
) : NoticeRepository {

    override suspend fun getNoticeData(url: String, linkUrl: String): ArrayList<NoticeItem> {

        return dataSource.getJsoupData(url, linkUrl).map {
            it.toModel()
        } as ArrayList<NoticeItem>

    }

}