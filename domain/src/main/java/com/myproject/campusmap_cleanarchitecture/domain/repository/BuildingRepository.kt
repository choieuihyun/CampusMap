package com.myproject.campusmap_cleanarchitecture.domain.repository

import android.content.Context
import android.widget.ImageView
import androidx.lifecycle.LiveData
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

    //suspend fun addBuildingFavorite(building: Building)
    suspend fun addBuildingFavorite(data: Any)

    suspend fun deleteBuildingFavorite(id: Int)

    // Building Image 불러오는 코드

    fun getBuildingImages(c: Context, path: String, v: ImageView)

    // SharedPreference

    fun getBuildingDetailCheckboxState(id: Int) : Boolean

    fun setBuildingDetailCheckboxState(id: Int, state: Boolean)
}