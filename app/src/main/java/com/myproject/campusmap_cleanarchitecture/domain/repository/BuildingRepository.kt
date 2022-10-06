package com.myproject.campusmap_cleanarchitecture.domain.repository

import androidx.lifecycle.LiveData
import com.campusmap.android.campusmap_with_kakao.data.db.local.entity.BuildingEntity

interface BuildingRepository {

    fun getBuildings(): LiveData<List<BuildingEntity>>

    fun getBuilding(): LiveData<BuildingEntity>


}