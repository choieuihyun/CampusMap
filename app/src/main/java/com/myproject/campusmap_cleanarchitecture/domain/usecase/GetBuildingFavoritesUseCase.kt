package com.myproject.campusmap_cleanarchitecture.domain.usecase

import androidx.lifecycle.LiveData
import com.myproject.campusmap_cleanarchitecture.domain.model.BuildingFavorite
import com.myproject.campusmap_cleanarchitecture.domain.repository.BuildingRepository
import javax.inject.Inject

class GetBuildingFavoritesUseCase @Inject constructor(
    private val repository: BuildingRepository
) {

    operator fun invoke() : LiveData<List<BuildingFavorite>> {
        return repository.getBuildingFavorites()
    }


}