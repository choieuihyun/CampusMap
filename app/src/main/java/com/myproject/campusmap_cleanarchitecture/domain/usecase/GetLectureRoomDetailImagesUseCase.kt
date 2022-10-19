package com.myproject.campusmap_cleanarchitecture.domain.usecase

import android.content.Context
import android.widget.ImageView
import com.myproject.campusmap_cleanarchitecture.domain.repository.LectureRoomRepository
import javax.inject.Inject

class GetLectureRoomDetailImagesUseCase @Inject constructor(
    private val repository: LectureRoomRepository
) {

    operator fun invoke(c: Context, path: String?, v: ImageView)  {
        repository.getLectureRoomImages(c, path, v)
    }
}