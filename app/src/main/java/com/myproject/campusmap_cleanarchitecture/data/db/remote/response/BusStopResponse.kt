package com.myproject.campusmap_cleanarchitecture.data.db.remote.response

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml
import java.io.Serializable

@Xml (name = "RFC30")
data class BusStopResponse (
        @PropertyElement
        val code: String,
        @PropertyElement
        val msg: String,
        @Element
        val routeList: BusStopList
        )

@Xml (name = "routeList")
data class BusStopList(
        @Element
        val list: BusStopData
)

@Xml (name = "list")
data class BusStopData(
        @PropertyElement(name = "reMark")
        val reMark: String,
        @PropertyElement(name = "searchFld")
        val searchFld: String,
        @PropertyElement(name = "searchNm")
        val searchNm: String,
        @PropertyElement(name = "stopId")
        val stopId: String,
        @PropertyElement(name = "stopKname")
        val stopKname: String,
        @PropertyElement(name = "stopStandardid")
        val stopStandardid: String,
        @PropertyElement(name = "stopX")
        val stopX: String,
        @PropertyElement(name = "stopY")
        val stopY: String
) : Serializable