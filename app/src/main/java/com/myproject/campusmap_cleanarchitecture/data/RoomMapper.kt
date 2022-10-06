package com.myproject.campusmap_cleanarchitecture.data

import com.campusmap.android.campusmap_with_kakao.data.db.local.entity.BuildingEntity
import com.myproject.campusmap_cleanarchitecture.domain.model.Building

fun Building.toEntity() = BuildingEntity(
    id = id,
    name = name,
    latitude = latitude,
    longitude = longitude
)

fun BuildingEntity.toModel() = Building(
    id = id,
    name = name,
    latitude = latitude,
    longitude = longitude
)