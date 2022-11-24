package com.myproject.campusmap_cleanarchitecture.data.db.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.myproject.campusmap_cleanarchitecture.data.db.local.entity.BuildingHistoryEntity

@Dao
interface BuildingHistoriesDao {

    @Query("SELECT * FROM BuildingHistory")
    fun getBuildingHistories(): LiveData<List<BuildingHistoryEntity>>

    @Insert
    suspend fun addBuilding(buildingHistoryEntity: BuildingHistoryEntity)

    @Query("DELETE FROM buildinghistory WHERE id = :id")
    suspend fun deleteBuilding(id: Int)

}