package com.myproject.campusmap_cleanarchitecture.data.db.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

// @Entity 뒤에 (tableName = "building") 요렇게도 가능한데
// 기본적으로 entity class 이름을 database table 이름으로 인식
@Entity(tableName="Building")
data class BuildingEntity(@PrimaryKey val id : Int,
                          var name: String = "",
                          var latitude: Double? = 0.0,
                          var longitude: Double? = 0.0,
                          var buildingImageUri : String? = "",
                          var category: String? = ""
                          /*@Embedded val eng : EngFirstLectureRoomEntity*/) {
}