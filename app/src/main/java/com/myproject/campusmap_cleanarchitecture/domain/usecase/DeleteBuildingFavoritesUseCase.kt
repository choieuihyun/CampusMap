package com.myproject.campusmap_cleanarchitecture.domain.usecase

import com.myproject.campusmap_cleanarchitecture.domain.repository.BuildingRepository
import javax.inject.Inject

class DeleteBuildingFavoritesUseCase @Inject constructor(
    private val repository: BuildingRepository
) {

    suspend operator fun invoke(id : Int) {
        repository.deleteBuildingFavorite(id)
    }

}