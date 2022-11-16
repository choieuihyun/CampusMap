package com.myproject.campusmap_cleanarchitecture.data

import com.myproject.campusmap_cleanarchitecture.data.db.remote.response.BusPositionData
import com.myproject.campusmap_cleanarchitecture.data.db.remote.response.BusStopData
import com.myproject.campusmap_cleanarchitecture.domain.NetworkResult
import com.myproject.campusmap_cleanarchitecture.domain.model.BusPosition
import com.myproject.campusmap_cleanarchitecture.domain.model.BusStop

fun BusPositionData.toModel() = BusPosition(
    brtStdid = brtStdid,
    bidNo = bidNo,
    RStop = rStop,
    RTime = convertTime(rTime),
    viaStopname = viaStopname,
    brtId = brtId,
    brtVianame = brtVianame.replace("-&gt;"," -> ")
)

fun BusStopData.toModel(direction: String) = BusStop(
    stopKname = "$stopKname\n($direction)",
    stopStandardid = stopStandardid,
    stopId = stopId,
    stopX = stopX,
    stopY = stopY,
    reMark = reMark
)

private fun convertTime(rTime: String) : String {
    val time = rTime.toInt()
    val convertMinute = time / 60
    val convertSecond = time % 60

    return ("$convertMinute 분 $convertSecond 초")
}

fun <T> NetworkResult<T>.toNetworkResult() : T =
    (this as NetworkResult.Success).data

private fun <R> changeNetworkData(replaceData: R) : NetworkResult<R> {
    return NetworkResult.Success(replaceData)
}

suspend fun <T, R> NetworkResult<T>.mapNetworkResult(getData: suspend (T) -> R): NetworkResult<R> {
    return changeNetworkData(getData(toNetworkResult()))
}
