package com.myproject.campusmap_cleanarchitecture.domain.usecase

import androidx.lifecycle.LiveData
import com.myproject.campusmap_cleanarchitecture.domain.model.BuildingHistory
import com.myproject.campusmap_cleanarchitecture.domain.repository.BuildingRepository
import javax.inject.Inject

class GetBuildingHistoriesUseCase @Inject constructor(
    private val repository: BuildingRepository
)  {

    operator fun invoke() : LiveData<List<BuildingHistory>> {
        return repository.getBuildingHistories()
    }
}