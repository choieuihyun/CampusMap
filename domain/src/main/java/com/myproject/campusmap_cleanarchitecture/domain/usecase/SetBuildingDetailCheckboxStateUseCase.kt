package com.myproject.campusmap_cleanarchitecture.domain.usecase

import com.myproject.campusmap_cleanarchitecture.domain.repository.BuildingRepository
import javax.inject.Inject

class SetBuildingDetailCheckboxStateUseCase @Inject constructor(
    private val repository: BuildingRepository
){

    fun setBuildingDetailCheckboxState(id: Int, state: Boolean) {
        repository.setBuildingDetailCheckboxState(id, state)
    }

}