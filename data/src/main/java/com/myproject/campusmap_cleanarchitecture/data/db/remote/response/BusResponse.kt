package com.myproject.campusmap_cleanarchitecture.data.db.remote.response
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml (name = "RFC30")
data class BusPositionResponse(
    @PropertyElement
    val code: String,
    @PropertyElement
    val msg: String,
    @Element
    val routeList: RouteList? // 두번째 정류장 데이터가 넘어오질 않음.
)

@Xml (name = "routeList")
data class RouteList (
    @Element
    val list: List<BusPositionData>? // 이하동문.
    )

@Xml (name = "list")
data class BusPositionData(
    @PropertyElement(name = "LKey")
    val lKey: String,
    @PropertyElement(name = "RStop")
    val rStop: String,
    @PropertyElement(name = "RTime")
    val rTime: String,
    @PropertyElement(name = "bidNo")
    val bidNo: String,
    @PropertyElement(name = "brtClass")
    val brtClass: String,
    @PropertyElement(name = "brtDirection")
    val brtDirection: String,
    @PropertyElement(name = "brtId")
    val brtId: String,
    @PropertyElement(name = "brtStdid")
    val brtStdid: String,
    @PropertyElement(name = "brtVianame")
    val brtVianame: String,
    @PropertyElement(name = "busLow")
    val busLow: String,
    @PropertyElement(name = "initBrtClass")
    val initBrtClass: String,
    @PropertyElement(name = "initBrtid")
    val initBrtid: String,
    @PropertyElement(name = "orderBy")
    val orderBy: String,
    @PropertyElement(name = "range")
    val range: String,
    @PropertyElement(name = "stopStdid")
    val stopStdid: String,
    @PropertyElement(name = "viaStopname")
    val viaStopname: String
)


