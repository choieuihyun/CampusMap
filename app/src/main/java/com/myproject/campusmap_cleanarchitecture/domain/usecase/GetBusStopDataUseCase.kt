package com.myproject.campusmap_cleanarchitecture.domain.usecase

import com.myproject.campusmap_cleanarchitecture.domain.model.BusStop
import com.myproject.campusmap_cleanarchitecture.domain.repository.BusRepository
import javax.inject.Inject

class GetBusStopDataUseCase @Inject constructor(
    private val repository: BusRepository
) {

    suspend operator fun invoke(searchNm: String, direction: String) : BusStop? {
        return repository.getBusStopData(searchNm, direction)
    }
}