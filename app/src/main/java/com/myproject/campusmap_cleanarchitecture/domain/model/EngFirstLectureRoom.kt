package com.myproject.campusmap_cleanarchitecture.domain.model

import java.io.Serializable

data class EngFirstLectureRoom (
    val id: Int,
    var name: String? = "",
    var floor: Int? = 0,
    var direction: String? = "",
    var route_1: String? = "",
    var route_2: String? = "",
    var route_3: String? = ""
)