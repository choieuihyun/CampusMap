package com.myproject.campusmap_cleanarchitecture.data

import com.myproject.campusmap_cleanarchitecture.data.db.remote.response.BusPositionData
import com.myproject.campusmap_cleanarchitecture.data.db.remote.response.BusStopData
import com.myproject.campusmap_cleanarchitecture.domain.model.BusPosition
import com.myproject.campusmap_cleanarchitecture.domain.model.BusStop

fun BusPositionData.toModel() = BusPosition(
    brtStdid = brtStdid,
    bidNo = bidNo,
    RStop = rStop,
    RTime = rTime,
    viaStopname = viaStopname,
    brtId = brtId,
    brtVianame = brtVianame
)

fun BusStopData.toModel(direction: String) = BusStop(
    stopKname = "$stopKname\n($direction)",
    stopStandardid = stopStandardid,
    stopId = stopId,
    stopX = stopX,
    stopY = stopY,
    reMark = reMark
)
