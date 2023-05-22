package com.myproject.campusmap_cleanarchitecture.data.db.remote.api

import com.myproject.campusmap_cleanarchitecture.data.db.remote.response.BusPositionResponse
import com.myproject.campusmap_cleanarchitecture.data.db.remote.response.BusStopResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response

interface BusApi {

    @GET("bus_location_low_busstop_infomation_common.do")
    suspend fun getBusPositionData(@Query("ServiceKey") ServiceKey : String,
                   @Query("stopStdid") stopStdid : Int) : Response<BusPositionResponse>

    @GET("bus_location2_stopno_common.do") // 정류장 번호로 검색
    suspend fun getBusStopData(@Query("ServiceKey") ServiceKey: String,
                               @Query("searchFld") searchFld: String,
                               @Query("searchNm") searchNm : String) : Response<BusStopResponse>


}