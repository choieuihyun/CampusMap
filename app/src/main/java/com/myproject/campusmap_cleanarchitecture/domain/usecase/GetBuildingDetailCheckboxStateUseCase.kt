package com.myproject.campusmap_cleanarchitecture.domain.usecase

import com.myproject.campusmap_cleanarchitecture.domain.repository.BuildingRepository
import javax.inject.Inject

class GetBuildingDetailCheckboxStateUseCase @Inject constructor(
    private val repository: BuildingRepository
){

    fun getBuildingDetailCheckboxState(id: Int) : Boolean {
        return repository.getBuildingDetailCheckboxState(id)
    }

}