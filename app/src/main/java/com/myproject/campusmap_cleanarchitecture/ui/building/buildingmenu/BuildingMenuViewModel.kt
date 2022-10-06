package com.campusmap.android.campusmap_with_kakao

import androidx.lifecycle.ViewModel

class BuildingMenuViewModel : ViewModel() {

    private val buildingRepository = BuildingRepository.get()
    val buildingsLiveData = buildingRepository.getBuildings()

}