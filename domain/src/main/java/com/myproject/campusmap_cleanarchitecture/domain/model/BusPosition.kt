package com.myproject.campusmap_cleanarchitecture.domain.model

data class BusPosition (
    val brtStdid: String, // 노선 ID
    val brtId: String, // 노선번호
    val bidNo: String, // 버스번호
    val RStop: String, // 남은 정류장수
    val RTime: String, // 도착예정시간(초단위)
    val viaStopname: String, // 최근 통과지점
    val brtVianame: String // 방면명
)