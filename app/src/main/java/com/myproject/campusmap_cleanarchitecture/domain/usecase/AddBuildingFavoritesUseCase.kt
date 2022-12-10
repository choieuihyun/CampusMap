package com.myproject.campusmap_cleanarchitecture.domain.usecase

import com.myproject.campusmap_cleanarchitecture.domain.model.Building
import com.myproject.campusmap_cleanarchitecture.domain.model.BuildingFavorite
import com.myproject.campusmap_cleanarchitecture.domain.model.BuildingHistory
import com.myproject.campusmap_cleanarchitecture.domain.repository.BuildingRepository
import javax.inject.Inject

class AddBuildingFavoritesUseCase @Inject constructor(
    private val repository: BuildingRepository
) {

    suspend fun addBuilding(building: Building) {
        repository.addBuildingFavorite(building)
    }

    suspend fun addBuilding(buildingHistory: BuildingHistory) {
        repository.addBuildingFavorite(buildingHistory)
    }
}