package com.myproject.campusmap_cleanarchitecture.data.datasource.localdatasource

import androidx.lifecycle.LiveData
import com.myproject.campusmap_cleanarchitecture.data.db.local.dao.LectureRoomDao
import com.myproject.campusmap_cleanarchitecture.data.db.local.database.BuildingDatabase
import com.myproject.campusmap_cleanarchitecture.data.db.local.entity.EngFirstLectureRoomEntity
import javax.inject.Inject

class LectureRoomDataSource @Inject constructor(
    private val buildingDatabase: BuildingDatabase
) {

    private val engFirstDao: LectureRoomDao = buildingDatabase.engFirstDao()

    fun getEngFirstLectureRooms() : LiveData<List<EngFirstLectureRoomEntity>> {
        return engFirstDao.getEngFirstLectureRooms()
    }

    fun getEngFirstLectureRoom(id: Int) : LiveData<EngFirstLectureRoomEntity?> {
        return engFirstDao.getEngFirstLectureRoom(id = id)
    }

}