package com.myproject.campusmap_cleanarchitecture.data.db.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.myproject.campusmap_cleanarchitecture.data.db.local.dao.BuildingDao
import com.myproject.campusmap_cleanarchitecture.data.db.local.dao.LectureRoomDao
import com.myproject.campusmap_cleanarchitecture.data.db.local.entity.BuildingEntity
import com.myproject.campusmap_cleanarchitecture.data.db.local.entity.EngFirstLectureRoomEntity

@Database(entities = [BuildingEntity::class, EngFirstLectureRoomEntity::class], version = 2)
abstract class BuildingDatabase : RoomDatabase() {

    abstract fun buildingDao(): BuildingDao

    abstract fun engFirstDao(): LectureRoomDao

}


