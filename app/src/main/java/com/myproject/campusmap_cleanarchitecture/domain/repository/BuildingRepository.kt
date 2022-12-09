package com.myproject.campusmap_cleanarchitecture.domain.repository

import android.app.Activity
import android.widget.ImageView
import androidx.lifecycle.LiveData
import com.myproject.campusmap_cleanarchitecture.data.db.local.entity.BuildingFavoriteEntity
import com.myproject.campusmap_cleanarchitecture.domain.model.Building
import com.myproject.campusmap_cleanarchitecture.domain.model.BuildingFavorite
import com.myproject.campusmap_cleanarchitecture.domain.model.BuildingHistory

interface BuildingRepository {

    // BuildingDB

    fun getBuildings(): LiveData<List<Building>>

    fun getBuilding(id: Int): LiveData<Building>

    // BuildingHistoryDB

    fun getBuildingHistories() : LiveData<List<BuildingHistory>>

    suspend fun addBuilding(building: Building)

    suspend fun deleteBuilding(id: Int)

    // BuildingFavoriteDB

    fun getBuildingFavorites() : LiveData<List<BuildingFavorite>>

    suspend fun addBuildingFavorite(building: Building)

    suspend fun deleteBuildingFavorite(id: Int)

    // Building Image 불러오는 코드

    fun getBuildingImages(c: Activity, path: String, v: ImageView)

    // SharedPreference

    fun getBuildingDetailCheckboxState(id: Int) : Boolean

    fun setBuildingDetailCheckboxState(id: Int, state: Boolean)
}