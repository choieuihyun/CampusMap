package com.myproject.campusmap_cleanarchitecture.presentation.search

import android.content.Context
import android.widget.ImageView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myproject.campusmap_cleanarchitecture.domain.model.Building
import com.myproject.campusmap_cleanarchitecture.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchFragmentViewModel @Inject constructor(
    private val buildingsUseCase: GetBuildingsUseCase,
    private val buildingsImagesUseCase: GetBuildingImagesUseCase,
    private val buildingHistoryUseCase: GetBuildingHistoriesUseCase,
    private val addBuildingHistoriesUseCase: AddBuildingHistoriesUseCase,
    private val deleteBuildingHistoriesUseCase: DeleteBuildingHistoriesUseCase,
) : ViewModel() {

    val getBuildings = buildingsUseCase.invoke()

    val getBuildingHistories = buildingHistoryUseCase.invoke()

    fun addBuildingHistories(building: Building) {
        viewModelScope.launch { // 왜 suspend를 안쓰는가에 대해서 -> Fragment단에서 Coroutine 안쓰려고? 쓰면 순서 꼬이잖아.
                                // 그리고 view랑 viewModel이 lifecycle이 달라서 이렇게 하는게 가장 큰 이유가 아닐까?
            addBuildingHistoriesUseCase.invoke(building)
        }
    }

    fun deleteBuildingHistories(id : Int) {
        viewModelScope.launch {
            deleteBuildingHistoriesUseCase.invoke(id)
        }
    }

    fun getBuildingImages(c: Context, path: String, v: ImageView) {
        buildingsImagesUseCase.invoke(c, path, v)
    }

}