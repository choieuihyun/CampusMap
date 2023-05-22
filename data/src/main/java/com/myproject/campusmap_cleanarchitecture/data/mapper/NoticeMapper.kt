package com.myproject.campusmap_cleanarchitecture.data.mapper

import com.myproject.campusmap_cleanarchitecture.data.db.remote.response.JsoupData
import com.myproject.campusmap_cleanarchitecture.domain.model.NoticeItem

fun JsoupData.toModel() = NoticeItem(

    info = info,
    link = link,
    number = number,
    title = title,
    writer = writer

)