package com.myproject.campusmap_cleanarchitecture.data.datasource.localdatasource

import android.app.Activity
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.internal.Storage
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.myproject.campusmap_cleanarchitecture.R
import com.myproject.campusmap_cleanarchitecture.data.db.local.database.BuildingDatabase
import com.myproject.campusmap_cleanarchitecture.data.db.local.entity.BuildingEntity
import com.myproject.campusmap_cleanarchitecture.data.di.NetworkModule
import javax.inject.Inject

class BuildingDataSource @Inject constructor(
    private val buildingDatabase: BuildingDatabase,
    private val firebaseStorage: FirebaseStorage
) {

    private val buildingDao = buildingDatabase.buildingDao()


    fun getBuildings() : LiveData<List<BuildingEntity>> {
        return buildingDao.getBuildings()
    }

    fun getBuilding(id: Int) : LiveData<BuildingEntity?> {
        return buildingDao.getBuilding(id = id)
    }

    fun getBuildingImages(c: Activity, path: String, v: ImageView) {

        val storageReference: StorageReference = firebaseStorage.reference
        val pathReference : StorageReference? = storageReference.child("photo")

        if (pathReference == null) {
            Toast.makeText(c as Activity?, "저장소에 사진이 없음", Toast.LENGTH_LONG).show()
        } else {
            val submitProfile: StorageReference = storageReference.child(path)
            submitProfile.downloadUrl.addOnSuccessListener { uri ->
                Glide.with(c).load(uri).fallback(R.drawable.ic_launcher_background).into(v)
            }.addOnFailureListener { }
        }
    }

/*    fun getBuildingImages(c: Activity, path: String, v: ImageView) {

        val storageReference : StorageReference = firebaseStorage.reference
        val pathReference : StorageReference? = storageReference.child("photo")

        if (pathReference == null) {
            Toast.makeText(c as Activity?, "저장소에 사진이 없음", Toast.LENGTH_LONG).show()
        } else {
            val submitProfile: StorageReference = storageReference.child(path)
            submitProfile.downloadUrl.addOnSuccessListener { uri ->
                Glide.with(c).load(uri).into(v)
            }.addOnFailureListener { }
        }
    }*/

}