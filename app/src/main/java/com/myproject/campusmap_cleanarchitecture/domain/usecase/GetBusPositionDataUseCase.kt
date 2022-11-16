package com.myproject.campusmap_cleanarchitecture.domain.usecase

import com.myproject.campusmap_cleanarchitecture.domain.NetworkResult
import com.myproject.campusmap_cleanarchitecture.domain.model.BusPosition
import com.myproject.campusmap_cleanarchitecture.domain.repository.BusRepository
import javax.inject.Inject

class GetBusPositionDataUseCase @Inject constructor(
    private val repository: BusRepository
) {

    suspend operator fun invoke(stopStdid: Int) : NetworkResult<List<BusPosition>?> {
        return repository.getBusPositionData(stopStdid)
    }

}