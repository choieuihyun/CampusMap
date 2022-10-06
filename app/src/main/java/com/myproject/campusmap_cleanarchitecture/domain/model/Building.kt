package com.myproject.campusmap_cleanarchitecture.domain.model

import java.io.Serializable

data class Building(
    val id : Int,
    var name: String = "",
    var latitude: Double? = 0.0,
    var longitude: Double? = 0.0,
    var buildingImageUri : String? = "" // 이 null 가능 최대한 안쓸수 있게 해보자.
) : Serializable // 나중에 즐겨찾기 같은거 할때 DB에 저장하려고 직렬화시킴.