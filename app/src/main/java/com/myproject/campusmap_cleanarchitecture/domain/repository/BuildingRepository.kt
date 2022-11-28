package com.myproject.campusmap_cleanarchitecture.domain.repository

import android.app.Activity
import android.widget.ImageView
import androidx.lifecycle.LiveData
import com.myproject.campusmap_cleanarchitecture.domain.model.Building
import com.myproject.campusmap_cleanarchitecture.domain.model.BuildingHistory

interface BuildingRepository {

    fun getBuildings(): LiveData<List<Building>>

    fun getBuilding(id: Int): LiveData<Building>

    fun getBuildingHistories() : LiveData<List<BuildingHistory>>

    suspend fun addBuilding(building: Building)

    suspend fun deleteBuilding(id: Int)

    fun getBuildingImages(c: Activity, path: String, v: ImageView)

}