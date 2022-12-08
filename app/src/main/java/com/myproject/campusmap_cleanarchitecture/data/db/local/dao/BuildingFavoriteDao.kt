package com.myproject.campusmap_cleanarchitecture.data.db.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.myproject.campusmap_cleanarchitecture.data.db.local.entity.BuildingFavorite

interface BuildingFavoriteDao {

    @Query("SELECT * FROM BuildingFavorite")
    fun getBuildingFavorites(): LiveData<List<BuildingFavorite>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBuildingFavorite(buildingFavorite: BuildingFavorite)

    @Query("DELETE FROM buildingfavorite WHERE id = :id")
    suspend fun deleteBuildingFavorite(id: Int)

}