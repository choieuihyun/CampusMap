package com.myproject.campusmap_cleanarchitecture.domain.model

import java.io.Serializable

data class Building(
    val id : Int,
    var name: String? = "",
    var latitude: Double? = 0.0,
    var longitude: Double? = 0.0,
    var buildingImageUri : String? = "",
    var category: String? = ""
) : Serializable // navigation에서 args로 보내기 위한 직렬화