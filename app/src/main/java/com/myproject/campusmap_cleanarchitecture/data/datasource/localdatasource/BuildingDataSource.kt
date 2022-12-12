package com.myproject.campusmap_cleanarchitecture.data.datasource.localdatasource

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.myproject.campusmap_cleanarchitecture.R
import com.myproject.campusmap_cleanarchitecture.data.db.local.database.BuildingDatabase
import com.myproject.campusmap_cleanarchitecture.data.db.local.database.BuildingFavoriteDatabase
import com.myproject.campusmap_cleanarchitecture.data.db.local.database.BuildingHistoriesDatabase
import com.myproject.campusmap_cleanarchitecture.data.db.local.entity.BuildingEntity
import com.myproject.campusmap_cleanarchitecture.data.db.local.entity.BuildingFavoriteEntity
import com.myproject.campusmap_cleanarchitecture.data.db.local.entity.BuildingHistoryEntity
import javax.inject.Inject

class BuildingDataSource @Inject constructor(
    private val buildingDatabase: BuildingDatabase,
    private val buildingHistoriesDatabase: BuildingHistoriesDatabase,
    private val buildingFavoritesDatabase: BuildingFavoriteDatabase,
    private val firebaseStorage: FirebaseStorage,
    private val sharedPreferences: SharedPreferences
) {

    private val buildingDao = buildingDatabase.buildingDao()
    private val buildingHistoryDao = buildingHistoriesDatabase.buildingHistoriesDao()
    private val buildingFavoriteDao = buildingFavoritesDatabase.buildingFavoriteDao()

    // BuildingDB

    fun getBuildings() : LiveData<List<BuildingEntity>> {
        return buildingDao.getBuildings()
    }

    fun getBuilding(id: Int) : LiveData<BuildingEntity?> {
        return buildingDao.getBuilding(id = id)
    }

    // BuildingHistoryDB

    fun getBuildingHistories() : LiveData<List<BuildingHistoryEntity>> {
        return buildingHistoryDao.getBuildingHistories()
    }

    suspend fun addBuilding(building : BuildingHistoryEntity) {
        buildingHistoryDao.addBuilding(building)
    }

    suspend fun deleteBuilding(id : Int) {
        buildingHistoryDao.deleteBuilding(id)
    }

    // BuildingFavoriteDB

    fun getBuildingFavorites() : LiveData<List<BuildingFavoriteEntity>> {
        return buildingFavoriteDao.getBuildingFavorites()
    }

    suspend fun addBuildingFavorite(buildingFavoriteEntity: BuildingFavoriteEntity) {
        buildingFavoriteDao.addBuildingFavorite(buildingFavoriteEntity)
    }

    suspend fun deleteBuildingFavorite(id: Int) {
        buildingFavoriteDao.deleteBuildingFavorite(id)
    }

    // Firebase Storage에서 Building Image 불러오는 코드

    fun getBuildingImages(c: Context, path: String?, v: ImageView) {

        val storageReference: StorageReference = firebaseStorage.reference

        try {
            val submitProfile: StorageReference = storageReference.child(path.toString())
            submitProfile.downloadUrl.addOnSuccessListener { uri ->
                Glide.with(c).load(uri).into(v)
            }.addOnFailureListener {
            }
        } catch (e : Exception) {
            Glide.with(c).load(R.drawable.no_image).into(v)
            Toast.makeText(c as Activity?, "저장소에 사진이 없음", Toast.LENGTH_LONG).show()
        }
    }

    // SharedPreference

    fun getBuildingDetailCheckboxState(id: Int) : Boolean {
        return sharedPreferences.getBoolean(id.toString(), false)
    }

    fun setBuildingDetailCheckboxState(id: Int, state: Boolean) {
        sharedPreferences.edit().putBoolean(id.toString(), state).apply()
    }

}