package com.myproject.campusmap_cleanarchitecture.data.repository

import android.app.Activity
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.myproject.campusmap_cleanarchitecture.data.datasource.localdatasource.BuildingDataSource
import com.myproject.campusmap_cleanarchitecture.data.toModel
import com.myproject.campusmap_cleanarchitecture.domain.model.Building
import com.myproject.campusmap_cleanarchitecture.domain.repository.BuildingRepository
import javax.inject.Inject

class BuildingRepositoryImpl @Inject constructor(
    private val dataSource: BuildingDataSource
) : BuildingRepository {

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

    override fun getBuildingImages(c: Activity, path: String, v: ImageView) {
        dataSource.getBuildingImages(c,path,v)
    }
}