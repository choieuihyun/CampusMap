package com.myproject.campusmap_cleanarchitecture.ui.lectureroom.lectureroommenu

import androidx.lifecycle.ViewModel
import com.myproject.campusmap_cleanarchitecture.domain.usecase.GetLectureRoomsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LectureRoomMenuViewModel @Inject constructor(
    private val lectureRoomUseCase: GetLectureRoomsUseCase
) : ViewModel() {

    val getEngFirstLectureRoom = lectureRoomUseCase.invoke()
}