package com.campusmap.android.campusmap_with_kakao.data.db.local.entity

import com.google.gson.annotations.SerializedName

data class BusResponse(
    @SerializedName("RFC30")
    val rFC30: RFC30?
)

data class RFC30(
    @SerializedName("code")
    val code: Code?,
    @SerializedName("msg")
    val msg: Msg?,
    @SerializedName("routeList")
    val routeList: RouteList?
)

data class RouteList(
    @SerializedName("_class")
    val classX: String?,
    @SerializedName("list")
    val list: List<BusData>?
)

data class Code(
    @SerializedName("__text")
    val text: String?,
    @SerializedName("_type")
    val type: String?
)

data class Msg(
    @SerializedName("__text")
    val text: String?,
    @SerializedName("_type")
    val type: String?
)

data class BusData(
    @SerializedName("bidNo")
    val bidNo: String?,
    @SerializedName("brtClass")
    val brtClass: String?,
    @SerializedName("brtDirection")
    val brtDirection: String?,
    @SerializedName("brtId")
    val brtId: String?,
    @SerializedName("brtStdid")
    val brtStdid: String?,
    @SerializedName("brtVianame")
    val brtVianame: String?,
    @SerializedName("busLow")
    val busLow: String?,
    @SerializedName("_class")
    val classX: String?,
    @SerializedName("RStop")
    val rStop: String?,
    @SerializedName("RTime")
    val rTime: String?,
    @SerializedName("viaStopname")
    val viaStopname: String?
)