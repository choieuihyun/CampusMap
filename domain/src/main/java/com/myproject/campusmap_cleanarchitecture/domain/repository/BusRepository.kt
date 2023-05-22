package com.myproject.campusmap_cleanarchitecture.domain.repository

import com.myproject.campusmap_cleanarchitecture.domain.error.NetworkResult
import com.myproject.campusmap_cleanarchitecture.domain.model.BusPosition
import com.myproject.campusmap_cleanarchitecture.domain.model.BusStop

interface BusRepository {

    suspend fun getBusPositionData(stopStdid: Int) : NetworkResult<List<BusPosition>?> // 여기 바뀜

    suspend fun getBusStopData(searchNm: String, direction: String) : BusStop?

}