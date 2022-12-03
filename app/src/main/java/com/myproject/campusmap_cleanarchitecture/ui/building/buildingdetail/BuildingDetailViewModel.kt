package com.myproject.campusmap_cleanarchitecture.ui.building.buildingdetail


import android.app.Activity
import android.widget.ImageView
import androidx.databinding.InverseMethod
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myproject.campusmap_cleanarchitecture.domain.model.Building
import com.myproject.campusmap_cleanarchitecture.domain.model.BuildingHistory
import com.myproject.campusmap_cleanarchitecture.domain.model.NoticeItem
import com.myproject.campusmap_cleanarchitecture.domain.usecase.GetBuildingHistoriesUseCase
import com.myproject.campusmap_cleanarchitecture.domain.usecase.GetBuildingImagesUseCase
import com.myproject.campusmap_cleanarchitecture.domain.usecase.GetBuildingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BuildingDetailViewModel @Inject constructor(
    private val buildingsImagesUseCase: GetBuildingImagesUseCase,
    private val buildingHistoriesUseCase: GetBuildingHistoriesUseCase,
    private val buildingUseCase: GetBuildingsUseCase
) : ViewModel() {

    private val _buildingDetailData = MutableLiveData<Any>()
    val buildingDetailData : LiveData<Any>
        get() = _buildingDetailData

    var _buildingName = MutableLiveData<String>()
/*    val buildingName : LiveData<String>
        get() = _buildingName*/

    fun getBuildingDetailData(data: Any) {

        viewModelScope.launch {

            when(data) {

                is Building -> {
                    _buildingDetailData.value = buildingUseCase.invoke()
                    _buildingName.value = data.name!!
                }

                is BuildingHistory -> {
                    _buildingDetailData.value = buildingHistoriesUseCase.invoke()
                    _buildingName.value = data.name!!
                }
            }

        }
    }


    fun getBuildingImages(c: Activity, path: String, v: ImageView) {
        buildingsImagesUseCase.invoke(c, path, v)
    }

}