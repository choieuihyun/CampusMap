package com.myproject.campusmap_cleanarchitecture.data.datasource.localdatasource

import android.content.Context
import android.widget.ImageView
import androidx.lifecycle.LiveData
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.myproject.campusmap_cleanarchitecture.data.R
import com.myproject.campusmap_cleanarchitecture.data.db.local.dao.LectureRoomDao
import com.myproject.campusmap_cleanarchitecture.data.db.local.database.BuildingDatabase
import com.myproject.campusmap_cleanarchitecture.data.db.local.entity.EngFirstLectureRoomEntity
import javax.inject.Inject

class LectureRoomDataSource @Inject constructor(
    private val buildingDatabase: BuildingDatabase,
    private val firebaseStorage: FirebaseStorage,
    private val storageReference: StorageReference = firebaseStorage.reference
) {

    private val engFirstDao: LectureRoomDao = buildingDatabase.engFirstDao()

    fun getEngFirstLectureRooms(): LiveData<List<EngFirstLectureRoomEntity>> {
        return engFirstDao.getEngFirstLectureRooms()
    }

    fun getEngFirstLectureRoom(id: Int): LiveData<EngFirstLectureRoomEntity?> {
        return engFirstDao.getEngFirstLectureRoom(id = id)
    }

    fun getLectureRoomImages(c: Context, path: String?, v: ImageView) {

        val storageReference: StorageReference = firebaseStorage.reference

        val submitProfile: StorageReference = storageReference.child(path.toString())
        submitProfile.downloadUrl.addOnSuccessListener { uri ->
            Glide.with(c).load(uri).into(v)
        }.addOnFailureListener {
            Glide.with(c).load(R.drawable.no_image).into(v)
        }
    }
}




