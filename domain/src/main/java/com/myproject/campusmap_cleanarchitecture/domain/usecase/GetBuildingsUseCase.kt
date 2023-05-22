package com.myproject.campusmap_cleanarchitecture.domain.usecase

import androidx.lifecycle.LiveData
import com.myproject.campusmap_cleanarchitecture.domain.model.Building
import com.myproject.campusmap_cleanarchitecture.domain.repository.BuildingRepository
import javax.inject.Inject

class GetBuildingsUseCase @Inject constructor(
    private val repository: BuildingRepository
) {
    operator fun invoke(): LiveData<List<Building>> {
        return repository.getBuildings()
    }
}