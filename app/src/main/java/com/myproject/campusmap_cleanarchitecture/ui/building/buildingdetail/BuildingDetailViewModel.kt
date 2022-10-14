package com.myproject.campusmap_cleanarchitecture.ui.building.buildingdetail


import android.app.Activity
import android.widget.ImageView
import androidx.lifecycle.ViewModel
import com.myproject.campusmap_cleanarchitecture.domain.usecase.GetBuildingImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BuildingDetailViewModel @Inject constructor(
    private val buildingsUseCase: GetBuildingImagesUseCase
) : ViewModel() {

    fun getBuildingImages(c: Activity, path: String, v: ImageView) {
        buildingsUseCase.invoke(c, path, v)
    }

/*    private val buildingRepository = BuildingRepository.get()
    val buildingsLiveData = buildingRepository.getBuildings()*/

}