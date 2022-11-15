package com.myproject.campusmap_cleanarchitecture.data.datasource.remotedatasource

import com.myproject.campusmap_cleanarchitecture.data.db.remote.api.BusApi
import com.myproject.campusmap_cleanarchitecture.data.db.remote.response.BusPositionData
import com.myproject.campusmap_cleanarchitecture.data.db.remote.response.BusStopData
import com.myproject.campusmap_cleanarchitecture.domain.NetworkErrorHandler
import com.myproject.campusmap_cleanarchitecture.domain.NetworkResult
import javax.inject.Inject

class BusDataSource @Inject constructor(
    private val api: BusApi,
    private val networkErrorHandler: NetworkErrorHandler
){
    suspend fun getBusPositionData(stopStdid: Int) : List<BusPositionData>? {

        // 사용해볼 수도 있기에 암호화 하지않음.
        val response = api.getBusPositionData("+seeu1lZNKYIcuFUa5fyGd0EywAbUdCfciBylbaoRdU9RzeZJFnxYWMaT0hKg0+wEzFlyM+iKr1wOD/jyx5i9w==", stopStdid)

        return response.body()?.routeList?.list
    }

    suspend fun getBusStopData(searchNm: String) : BusStopData? {

        val response = api.getBusStopData("+seeu1lZNKYIcuFUa5fyGd0EywAbUdCfciBylbaoRdU9RzeZJFnxYWMaT0hKg0+wEzFlyM+iKr1wOD/jyx5i9w==","stopno", searchNm)

        return response.body()?.routeList?.list
    }
}