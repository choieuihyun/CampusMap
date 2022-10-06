package com.campusmap.android.campusmap_with_kakao.data.db.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.campusmap.android.campusmap_with_kakao.data.db.local.dao.BuildingDao
import com.campusmap.android.campusmap_with_kakao.data.db.local.entity.BuildingEntity
import com.campusmap.android.campusmap_with_kakao.data.db.local.entity.EngFirstLectureRoomEntity

// 이 클래스가 앱의 데이터베이스를 나타낸다고 Room에게 알려줌
// 그리고 여기에 Dao의 참조를 가진 후 Dao에 정의된 함수들을 호출해서 DB를 사용하겠다.
@Database(entities = [BuildingEntity::class, EngFirstLectureRoomEntity::class], version = 2)
abstract class BuildingDatabase : RoomDatabase() {

    abstract fun buildingDao(): BuildingDao

}

// entities = [Building::class] 원래는 이렇게 entity 설정인데
// entity가 여러개면 arrayOf(Building::class, Eng1LectureRoom::class) 이렇게 함 안해도 되는데?


