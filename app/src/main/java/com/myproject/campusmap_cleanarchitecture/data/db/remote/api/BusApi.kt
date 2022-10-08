package com.myproject.campusmap_cleanarchitecture.data.db.remote.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import com.myproject.campusmap_cleanarchitecture.data.db.remote.response.RouteList

interface BusApi {

    @GET("bus_location_low_busstop_infomation_common.do")
    fun getBusData(@Query("ServiceKey") ServiceKey : String,
                   @Query("stopStdid") stopStdid : String) : Call<RouteList>



}