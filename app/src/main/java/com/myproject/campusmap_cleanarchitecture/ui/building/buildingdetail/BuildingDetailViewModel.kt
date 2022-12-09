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
import com.myproject.campusmap_cleanarchitecture.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BuildingDetailViewModel @Inject constructor(
    private val buildingsImagesUseCase: GetBuildingImagesUseCase,
    private val buildingHistoriesUseCase: GetBuildingHistoriesUseCase,
    private val buildingUseCase: GetBuildingsUseCase,
    private val getBuildingDetailCheckboxStateUseCase: GetBuildingDetailCheckboxStateUseCase,
    private val setBuildingDetailCheckboxStateUseCase: SetBuildingDetailCheckboxStateUseCase
) : ViewModel() {

    private val _buildingDetailData = MutableLiveData<Any>()
    val buildingDetailData : LiveData<Any>
        get() = _buildingDetailData

    var buildingName = MutableLiveData<String>() // 양방향 데이터 바인딩은 observable한 데이터로 한다길래..

    var checkboxState : Boolean = false

    fun getBuildingDetailData(data: Any) {

        viewModelScope.launch {

            when(data) {

                is Building -> {
                    _buildingDetailData.value = buildingUseCase.invoke()
                    buildingName.value = data.name!!
                }

                is BuildingHistory -> {
                    _buildingDetailData.value = buildingHistoriesUseCase.invoke()
                    buildingName.value = data.name!!
                }
            }

        }
    }

    fun getBuildingImages(c: Activity, path: String, v: ImageView) {
        buildingsImagesUseCase.invoke(c, path, v)
    }

    fun getBuildingDetailCheckboxState(id: Int) : Boolean {
        return getBuildingDetailCheckboxStateUseCase.getBuildingDetailCheckboxState(id)
    }

    fun setBuildingDetailCheckboxState(id: Int, state: Boolean) {
        setBuildingDetailCheckboxStateUseCase.setBuildingDetailCheckboxState(id,state)
    }
}