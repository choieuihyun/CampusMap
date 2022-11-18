package com.myproject.campusmap_cleanarchitecture.data.datasource.remotedatasource

import com.myproject.campusmap_cleanarchitecture.data.db.remote.api.BusApi
import com.myproject.campusmap_cleanarchitecture.data.db.remote.response.BusPositionData
import com.myproject.campusmap_cleanarchitecture.data.db.remote.response.BusStopData
import com.myproject.campusmap_cleanarchitecture.domain.error.NetworkErrorHandler
import com.myproject.campusmap_cleanarchitecture.domain.error.NetworkResult
import javax.inject.Inject

class BusDataSource @Inject constructor(
    private val api: BusApi,
    private val networkErrorHandler: NetworkErrorHandler
){
    suspend fun getBusPositionData(stopStdid: Int) : NetworkResult<List<BusPositionData>?> {

        // 테스트를 해볼 수도 있기에 암호화 하지않음.
        val response = api.getBusPositionData("+seeu1lZNKYIcuFUa5fyGd0EywAbUdCfciBylbaoRdU9RzeZJFnxYWMaT0hKg0+wEzFlyM+iKr1wOD/jyx5i9w==", stopStdid)

        return try {
            NetworkResult.Success(response.body()?.routeList?.list)
        } catch (exception: Exception) {
            val errorType = networkErrorHandler.getError(exception)
            NetworkResult.Error(errorType)
        }
    }

    suspend fun getBusStopData(searchNm: String) : NetworkResult<BusStopData?> {

        val response = api.getBusStopData("+seeu1lZNKYIcuFUa5fyGd0EywAbUdCfciBylbaoRdU9RzeZJFnxYWMaT0hKg0+wEzFlyM+iKr1wOD/jyx5i9w==","stopno", searchNm)

        return try {
            NetworkResult.Success(response.body()?.routeList?.list)
        } catch (exception: Exception) {
            val errorType = networkErrorHandler.getError(exception)
            NetworkResult.Error(errorType)
        }
    }
}