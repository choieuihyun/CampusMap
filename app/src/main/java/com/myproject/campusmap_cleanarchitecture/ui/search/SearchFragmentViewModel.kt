package com.myproject.campusmap_cleanarchitecture.ui.search

import androidx.lifecycle.ViewModel
import com.myproject.campusmap_cleanarchitecture.domain.usecase.GetBuildingsUseCase
import com.myproject.campusmap_cleanarchitecture.domain.usecase.GetLectureRoomsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchFragmentViewModel @Inject constructor(
    private val buildingsUseCase: GetBuildingsUseCase,
    private val lectureRoomsUseCase: GetLectureRoomsUseCase
) : ViewModel() {

    val getBuildings = buildingsUseCase.invoke()

    val getLectureRooms = lectureRoomsUseCase.invoke()

}