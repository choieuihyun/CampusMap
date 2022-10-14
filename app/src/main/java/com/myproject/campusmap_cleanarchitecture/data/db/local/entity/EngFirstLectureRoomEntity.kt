package com.myproject.campusmap_cleanarchitecture.data.db.local.entity
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="Eng1LectureRoom")
data class EngFirstLectureRoomEntity(@PrimaryKey val id: Int,
                                     var name: String? = "",
                                     var floor: Int? = 0,
                                     var direction: String? = "",
                                     var route_1: String? = "",
                                     var route_2: String? = "",
                                     var route_3: String? = ""){
}