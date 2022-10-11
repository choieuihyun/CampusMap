package com.myproject.campusmap_cleanarchitecture.data

import com.myproject.campusmap_cleanarchitecture.data.db.local.entity.BuildingEntity
import com.myproject.campusmap_cleanarchitecture.domain.model.Building

fun Building.toEntity() = BuildingEntity(
    id = id,
    name = name,
    latitude = latitude,
    longitude = longitude,
    buildingImageUri = buildingImageUri
)

fun BuildingEntity.toModel() = Building(
    id = id,
    name = name,
    latitude = latitude,
    longitude = longitude,
    buildingImageUri = buildingImageUri
)