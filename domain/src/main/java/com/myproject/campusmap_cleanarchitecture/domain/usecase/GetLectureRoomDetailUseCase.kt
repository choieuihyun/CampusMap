package com.myproject.campusmap_cleanarchitecture.domain.usecase

import androidx.lifecycle.LiveData
import com.myproject.campusmap_cleanarchitecture.domain.model.LectureRoom
import com.myproject.campusmap_cleanarchitecture.domain.repository.LectureRoomRepository
import javax.inject.Inject

class GetLectureRoomDetailUseCase @Inject constructor(
    private val repository: LectureRoomRepository
) {
    operator fun invoke(id: Int): LiveData<LectureRoom> {
        return repository.getEngFirstLectureRoom(id)
    }
}