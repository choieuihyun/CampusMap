package com.myproject.campusmap_cleanarchitecture.domain.model

import java.io.Serializable

data class BuildingHistory (
    val id : Int,
    var name: String? = "",
    var latitude: Double? = 0.0,
    var longitude: Double? = 0.0,
    var buildingImageUri : String? = "",
    var category: String? = ""
) : Serializable