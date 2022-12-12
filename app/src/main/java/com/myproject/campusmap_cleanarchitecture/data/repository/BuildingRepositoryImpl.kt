package com.myproject.campusmap_cleanarchitecture.data.repository

import android.app.Activity
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.myproject.campusmap_cleanarchitecture.data.datasource.localdatasource.BuildingDataSource
import com.myproject.campusmap_cleanarchitecture.data.toBuildingFavoriteEntity
import com.myproject.campusmap_cleanarchitecture.data.toHistoryEntity
import com.myproject.campusmap_cleanarchitecture.data.toModel
import com.myproject.campusmap_cleanarchitecture.domain.model.Building
import com.myproject.campusmap_cleanarchitecture.domain.model.BuildingFavorite
import com.myproject.campusmap_cleanarchitecture.domain.model.BuildingHistory
import com.myproject.campusmap_cleanarchitecture.domain.repository.BuildingRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class BuildingRepositoryImpl @Inject constructor(
    private val dataSource: BuildingDataSource
) : BuildingRepository {

    // BuildingDB

    override fun getBuildings(): LiveData<List<Building>> {
        return dataSource.getBuildings().map { entities ->
            entities.map { entity ->
                entity.toModel()
            }
        }
    }

    override fun getBuilding(id: Int): LiveData<Building> {
        return dataSource.getBuilding(id = id).map {
            it!!.toModel()
        }
    }

    // BuildingHistoryDB

    override fun getBuildingHistories(): LiveData<List<BuildingHistory>> {
        return dataSource.getBuildingHistories().map {
            buildingHistoryEntities -> buildingHistoryEntities.map {
                buildingHistoryEntity -> buildingHistoryEntity.toModel()
            }
        }
    }

    override suspend fun addBuilding(building: Building) {
        dataSource.addBuilding(building.toHistoryEntity())
    }

    override suspend fun deleteBuilding(id: Int) {
        dataSource.deleteBuilding(id)
    }

    // BuildingFavoriteDB

    override fun getBuildingFavorites(): LiveData<List<BuildingFavorite>> {
        return dataSource.getBuildingFavorites().map {
                buildingFavoriteEntities -> buildingFavoriteEntities.map {
                buildingFavoriteEntity -> buildingFavoriteEntity.toModel()
            }
        }
    }

//    override suspend fun addBuildingFavorite(building : Building) {
//        return dataSource.addBuildingFavorite(building.toBuildingFavoriteEntity())
//    }
    override suspend fun addBuildingFavorite(data : Any) {

        return when(data) {
            is Building -> dataSource.addBuildingFavorite(data.toBuildingFavoriteEntity())

            is BuildingHistory -> dataSource.addBuildingFavorite(data.toBuildingFavoriteEntity())

            else -> { } // 에러처리를 여기서 ?
        }
    }

    override suspend fun deleteBuildingFavorite(id: Int) {
        return dataSource.deleteBuildingFavorite(id)
    }

    // BuildingImage 불러오는 코드

    override fun getBuildingImages(c: Activity, path: String, v: ImageView) {
        dataSource.getBuildingImages(c,path,v)
    }

    // BuildingDetail Checkbox

    override fun getBuildingDetailCheckboxState(id: Int) : Boolean {
        return dataSource.getBuildingDetailCheckboxState(id)
    }

    override fun setBuildingDetailCheckboxState(id: Int, state: Boolean) {
        dataSource.setBuildingDetailCheckboxState(id, state)
    }
}