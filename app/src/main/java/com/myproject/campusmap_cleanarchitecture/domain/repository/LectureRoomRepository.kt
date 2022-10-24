package com.myproject.campusmap_cleanarchitecture.domain.repository

import android.content.Context
import android.widget.ImageView
import androidx.lifecycle.LiveData
import com.myproject.campusmap_cleanarchitecture.domain.model.LectureRoom

interface LectureRoomRepository {

    fun getEngFirstLectureRooms() : LiveData<List<LectureRoom>>

    fun getEngFirstLectureRoom(id: Int) : LiveData<LectureRoom>

    fun getLectureRoomImages(c: Context, path: String?, v: ImageView)

}