package com.campusmap.android.campusmap_with_kakao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.campusmap.android.campusmap_with_kakao.building.Building

class SearchFragmentViewModel : ViewModel() {

    private val buildingRepository: BuildingRepository = BuildingRepository.get()
    private val buildingIdLiveData = MutableLiveData<Int>()
    val buildingsData = buildingRepository.getBuildings()

    // 마커를 띄워줄 mapFragment에 보여줄 Building 객체를 저장한 LiveData 준비
    var buildingLiveData: LiveData<Building> =
        Transformations.switchMap(buildingIdLiveData) { buildingId ->
            buildingRepository.getBuilding(buildingId)
        }

    fun loadBuilding(id: Int) {
        buildingIdLiveData.value = id
    }

}