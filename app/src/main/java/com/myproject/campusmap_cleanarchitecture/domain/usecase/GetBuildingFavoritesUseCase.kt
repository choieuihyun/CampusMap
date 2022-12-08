package com.myproject.campusmap_cleanarchitecture.domain.usecase

import com.myproject.campusmap_cleanarchitecture.domain.repository.BuildingRepository
import javax.inject.Inject

class GetBuildingFavoritesUseCase @Inject constructor(
    private val repository: BuildingRepository
) {

    operator fun invoke() {
        repository.getBuildingFavorites()
    }

}