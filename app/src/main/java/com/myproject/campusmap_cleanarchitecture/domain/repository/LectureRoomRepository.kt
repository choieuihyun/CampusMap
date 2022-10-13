package com.myproject.campusmap_cleanarchitecture.domain.repository

import androidx.lifecycle.LiveData
import com.myproject.campusmap_cleanarchitecture.domain.model.EngFirstLectureRoom
import com.myproject.campusmap_cleanarchitecture.domain.model.LectureRoom

interface LectureRoomRepository {

    fun getEngFirstLectureRooms() : LiveData<List<LectureRoom>>

    fun getEngFirstLectureRoom(id: Int) : LiveData<LectureRoom>

}