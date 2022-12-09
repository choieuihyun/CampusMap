package com.myproject.campusmap_cleanarchitecture.domain.usecase

import com.myproject.campusmap_cleanarchitecture.domain.model.BuildingFavorite
import com.myproject.campusmap_cleanarchitecture.domain.repository.BuildingRepository
import javax.inject.Inject

class AddBuildingFavoritesUseCase @Inject constructor(
    private val repository: BuildingRepository
) {

    suspend operator fun invoke(buildingFavorite: BuildingFavorite) {
        repository.addBuildingFavorite(buildingFavorite)
    }
}