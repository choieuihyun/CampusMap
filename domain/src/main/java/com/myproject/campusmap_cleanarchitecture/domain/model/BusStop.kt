package com.myproject.campusmap_cleanarchitecture.domain.model

data class BusStop (
    val stopKname: String? = "",
    val stopStandardid: String,
    val stopId: String,
    val stopX: String,
    val stopY: String,
    val reMark: String
    ) : java.io.Serializable