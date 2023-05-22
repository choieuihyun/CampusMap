package com.myproject.campusmap_cleanarchitecture.data.db.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="BuildingHistory")
data class BuildingHistoryEntity (
    @PrimaryKey val id : Int,
    var name: String? = "",
    var latitude: Double? = 0.0,
    var longitude: Double? = 0.0,
    var buildingImageUri : String? = "",
    var category: String? = ""
)