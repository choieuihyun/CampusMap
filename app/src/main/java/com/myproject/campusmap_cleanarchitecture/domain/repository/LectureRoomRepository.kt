package com.myproject.campusmap_cleanarchitecture.domain.repository

import androidx.lifecycle.LiveData
import com.myproject.campusmap_cleanarchitecture.data.db.local.entity.EngFirstLectureRoomEntity

interface LectureRoomRepository {

    fun getEngFirstLectureRooms() : LiveData<List<EngFirstLectureRoomEntity>>

    fun getEngFirstLectureRoom() : LiveData<EngFirstLectureRoomEntity>

}