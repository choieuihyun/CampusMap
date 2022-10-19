package com.myproject.campusmap_cleanarchitecture.data.repository

import android.content.Context
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.myproject.campusmap_cleanarchitecture.data.datasource.localdatasource.LectureRoomDataSource
import com.myproject.campusmap_cleanarchitecture.data.toModel
import com.myproject.campusmap_cleanarchitecture.domain.model.LectureRoom
import com.myproject.campusmap_cleanarchitecture.domain.repository.LectureRoomRepository
import javax.inject.Inject

class LectureRoomRepositoryImpl @Inject constructor(
    private val dataSource: LectureRoomDataSource
) : LectureRoomRepository{

    override fun getEngFirstLectureRooms(): LiveData<List<LectureRoom>> {
        return dataSource.getEngFirstLectureRooms().map { entities ->
            entities.map { entity ->
                entity.toModel()
            }
        }
    }

    override fun getEngFirstLectureRoom(id: Int): LiveData<LectureRoom> {
        return dataSource.getEngFirstLectureRoom(id = id).map {
            it!!.toModel()
        }
    }

    override fun getLectureRoomImages(c: Context, path: String?, v: ImageView) {
        dataSource.getLectureRoomImages(c,path,v)
    }

}