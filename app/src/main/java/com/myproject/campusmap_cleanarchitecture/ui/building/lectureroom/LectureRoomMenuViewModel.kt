package com.campusmap.android.campusmap_with_kakao.lectureroom

import androidx.lifecycle.ViewModel
import com.campusmap.android.campusmap_with_kakao.BuildingRepository

class LectureRoomMenuViewModel : ViewModel() {

    private val lectureRoomRepository: BuildingRepository = BuildingRepository.get()
    var buildingLectureRoomLiveData = lectureRoomRepository.getEng1LectureRooms()


}