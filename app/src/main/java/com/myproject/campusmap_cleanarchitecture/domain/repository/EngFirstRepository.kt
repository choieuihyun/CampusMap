package com.myproject.campusmap_cleanarchitecture.domain.repository

import androidx.lifecycle.LiveData
import com.campusmap.android.campusmap_with_kakao.data.db.local.entity.EngFirstLectureRoomEntity

interface EngFirstRepository {

    fun getEngFirstLectureRooms() : LiveData<List<EngFirstLectureRoomEntity>>

    fun getEngFirstLectureRoom() : LiveData<EngFirstLectureRoomEntity>

}