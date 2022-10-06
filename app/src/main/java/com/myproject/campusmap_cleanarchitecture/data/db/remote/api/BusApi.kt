package com.campusmap.android.campusmap_with_kakao.data.db.remote.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import com.campusmap.android.campusmap_with_kakao.data.db.local.entity.RouteList

interface BusApi {

    @GET("bus_location_low_busstop_infomation_common.do")
    fun getBusData(@Query("ServiceKey") ServiceKey : String,
                   @Query("stopStdid") stopStdid : String) : Call<RouteList>



}