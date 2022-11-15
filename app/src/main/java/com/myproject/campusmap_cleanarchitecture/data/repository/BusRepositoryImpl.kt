package com.myproject.campusmap_cleanarchitecture.data.repository

import com.myproject.campusmap_cleanarchitecture.data.datasource.remotedatasource.BusDataSource
import com.myproject.campusmap_cleanarchitecture.data.toModel
import com.myproject.campusmap_cleanarchitecture.domain.model.BusPosition
import com.myproject.campusmap_cleanarchitecture.domain.model.BusStop
import com.myproject.campusmap_cleanarchitecture.domain.repository.BusRepository
import javax.inject.Inject

class BusRepositoryImpl @Inject constructor(
    private val dataSource: BusDataSource
) : BusRepository {

    override suspend fun getBusPositionData(stopStdid: Int): List<BusPosition>? { // isSuccessful 사용해서 error 처리해야할듯.

        return dataSource.getBusPositionData(stopStdid)?.map {
            busData ->
            busData.toModel()
        }

    }

    override suspend fun getBusStopData(searchNm: String, direction: String) : BusStop? {

        return dataSource.getBusStopData(searchNm)?.toModel(direction)

    }

}