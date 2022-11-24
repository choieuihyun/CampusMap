package com.myproject.campusmap_cleanarchitecture.data

import com.myproject.campusmap_cleanarchitecture.data.db.local.entity.BuildingEntity
import com.myproject.campusmap_cleanarchitecture.data.db.local.entity.BuildingHistoryEntity
import com.myproject.campusmap_cleanarchitecture.data.db.local.entity.EngFirstLectureRoomEntity
import com.myproject.campusmap_cleanarchitecture.domain.model.Building
import com.myproject.campusmap_cleanarchitecture.domain.model.BuildingHistory
import com.myproject.campusmap_cleanarchitecture.domain.model.LectureRoom

fun BuildingEntity.toModel() = Building(
    id = id,
    name = name,
    latitude = latitude,
    longitude = longitude,
    buildingImageUri = buildingImageUri,
    category = category
)

fun Building.toHistoryEntity() = BuildingHistoryEntity(
    id = id,
    name = name,
    latitude = latitude,
    longitude = longitude,
    buildingImageUri = buildingImageUri,
    category = category
)

fun BuildingHistoryEntity.toModel() = BuildingHistory(
    id = id,
    name = name,
    latitude = latitude,
    longitude = longitude,
    buildingImageUri = buildingImageUri,
    category = category
)

fun EngFirstLectureRoomEntity.toModel() = LectureRoom(
    id = id,
    name = name,
    floor = floor,
    direction = direction,
    route_1 = route_1,
    route_2 = route_2,
    route_3 = route_3
)

