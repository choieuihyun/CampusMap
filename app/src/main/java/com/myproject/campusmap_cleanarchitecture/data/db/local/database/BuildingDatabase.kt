package com.myproject.campusmap_cleanarchitecture.data.db.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Update
import com.myproject.campusmap_cleanarchitecture.data.db.local.dao.BuildingDao
import com.myproject.campusmap_cleanarchitecture.data.db.local.dao.BuildingHistoriesDao
import com.myproject.campusmap_cleanarchitecture.data.db.local.dao.LectureRoomDao
import com.myproject.campusmap_cleanarchitecture.data.db.local.entity.BuildingEntity
import com.myproject.campusmap_cleanarchitecture.data.db.local.entity.BuildingHistoryEntity
import com.myproject.campusmap_cleanarchitecture.data.db.local.entity.EngFirstLectureRoomEntity
import com.myproject.campusmap_cleanarchitecture.domain.model.BuildingHistory

@Database(entities = [BuildingEntity::class, EngFirstLectureRoomEntity::class], version = 2)
abstract class BuildingDatabase : RoomDatabase() {

    abstract fun buildingDao(): BuildingDao

    abstract fun engFirstDao(): LectureRoomDao

}

// 위에꺼랑 같이 entity 쓰지 않은 이유
// BuildingDatabase는 내가 이미 만들어놓은 DB이고 이건 내가 따로 저장할 검색기록
// 사실 내가 이미 데이터를 넣어놓은 DB에 BuildingHistoryEntity를 추가했다면 됐겠지만 내가 따로 관리하는 DB와
// 사용자가 추가할 데이터가 같이 있는것은 안된다고 판단해서 이렇게 만들었음. 무결성 침해?
@Database(entities = [BuildingHistoryEntity::class], version = 1)
abstract class BuildingHistoriesDatabase : RoomDatabase() {

    abstract fun buildingHistoriesDao(): BuildingHistoriesDao

}

