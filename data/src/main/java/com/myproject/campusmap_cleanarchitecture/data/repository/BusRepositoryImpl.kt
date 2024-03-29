package com.myproject.campusmap_cleanarchitecture.data.repository

import com.myproject.campusmap_cleanarchitecture.data.datasource.remotedatasource.BusDataSource
import com.myproject.campusmap_cleanarchitecture.data.mapper.mapNetworkResult
import com.myproject.campusmap_cleanarchitecture.data.mapper.toModel
import com.myproject.campusmap_cleanarchitecture.data.mapper.toNetworkResult
import com.myproject.campusmap_cleanarchitecture.domain.error.NetworkResult
import com.myproject.campusmap_cleanarchitecture.domain.model.BusPosition
import com.myproject.campusmap_cleanarchitecture.domain.model.BusStop
import com.myproject.campusmap_cleanarchitecture.domain.repository.BusRepository
import javax.inject.Inject

class BusRepositoryImpl @Inject constructor(
    private val dataSource: BusDataSource
) : BusRepository {

    override suspend fun getBusPositionData(stopStdid: Int): NetworkResult<List<BusPosition>?> { // isSuccessful 사용해서 error 처리해야할듯.

        return dataSource.getBusPositionData(stopStdid).mapNetworkResult {
                busPositionData -> busPositionData?.map {
                it.toModel()
            }
        }
    }

    override suspend fun getBusStopData(searchNm: String, direction: String): BusStop? {

        return dataSource.getBusStopData(searchNm).toNetworkResult()?.toModel(direction)

    }

}