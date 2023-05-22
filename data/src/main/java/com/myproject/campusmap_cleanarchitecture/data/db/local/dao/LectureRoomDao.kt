package com.myproject.campusmap_cleanarchitecture.data.db.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.myproject.campusmap_cleanarchitecture.data.db.local.entity.EngFirstLectureRoomEntity


@Dao
interface LectureRoomDao {

    // 공학1관 Dao
    @Query("SELECT * FROM Eng1LectureRoom")
    fun getEngFirstLectureRooms(): LiveData<List<EngFirstLectureRoomEntity>>

    @Query("SELECT * FROM Eng1LectureRoom WHERE id = (:id)")
    fun getEngFirstLectureRoom(id : Int): LiveData<EngFirstLectureRoomEntity?>

}