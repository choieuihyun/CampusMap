package com.myproject.campusmap_cleanarchitecture.presentation.lectureroom.lectureroommenu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myproject.campusmap_cleanarchitecture.domain.model.Building
import com.myproject.campusmap_cleanarchitecture.domain.model.BuildingHistory
import com.myproject.campusmap_cleanarchitecture.domain.usecase.GetLectureRoomsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LectureRoomMenuViewModel @Inject constructor(
    private val lectureRoomUseCase: GetLectureRoomsUseCase
) : ViewModel() {

    private val _lectureRoomMenuData = MutableLiveData<Any>()
    val lectureRoomMenuData : LiveData<Any>
        get() = _lectureRoomMenuData

    var lectureRoomName = MutableLiveData<String>()

    val getEngFirstLectureRoom = lectureRoomUseCase.invoke()

    fun getLectureRoomDetailData(data: Any) {

        viewModelScope.launch {

            when(data) {
                is Building -> {
                    lectureRoomName.value = data.name!!
                }
                is BuildingHistory -> {
                    lectureRoomName.value = data.name!!
                }
            }

        }

    }

}