package com.myproject.campusmap_cleanarchitecture.ui.lectureroom.lectureroomdetail

import android.content.Context
import android.widget.ImageView
import androidx.lifecycle.ViewModel
import com.myproject.campusmap_cleanarchitecture.domain.usecase.GetLectureRoomDetailImagesUseCase
import com.myproject.campusmap_cleanarchitecture.domain.usecase.GetLectureRoomDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LectureRoomDetailViewModel @Inject constructor(
    private val getLectureRoomUseCase: GetLectureRoomDetailUseCase,
    private val getLectureRoomDetailImagesUseCase: GetLectureRoomDetailImagesUseCase
) : ViewModel(){



    fun getEngFirstLectureRoomDetail(id: Int) = getLectureRoomUseCase.invoke(id)

    fun getImagesUseCase(c: Context, path: String?, v: ImageView) = getLectureRoomDetailImagesUseCase.invoke(c, path, v)

}