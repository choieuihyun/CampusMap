package com.myproject.campusmap_cleanarchitecture.presentation.building.buildingfavorite

import android.content.Context
import android.widget.ImageView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myproject.campusmap_cleanarchitecture.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BuildingFavoriteViewModel @Inject constructor(
    private val getBuildingFavoriteUseCase : GetBuildingFavoritesUseCase,
    private val getBuildingImagesUseCase: GetBuildingImagesUseCase,
    private val deleteBuildingFavoritesUseCase: DeleteBuildingFavoritesUseCase,
    private val getBuildingDetailCheckboxStateUseCase: GetBuildingDetailCheckboxStateUseCase,
    private val setBuildingDetailCheckboxStateUseCase: SetBuildingDetailCheckboxStateUseCase
) : ViewModel() {

    val getBuildingFavorites = getBuildingFavoriteUseCase.invoke()

    var checkboxState : Boolean = false

    fun getBuildingImages(c: Context, path: String, v: ImageView) {
        getBuildingImagesUseCase.invoke(c, path, v)
    }

    fun deleteBuildingFavorite(id: Int) {
        viewModelScope.launch {
            deleteBuildingFavoritesUseCase.invoke(id)
        }
    }

    // Checkbox

    fun getBuildingDetailCheckboxState(id: Int) : Boolean {
        return getBuildingDetailCheckboxStateUseCase.getBuildingDetailCheckboxState(id)
    }

    fun setBuildingDetailCheckboxState(id: Int, state: Boolean) {
        setBuildingDetailCheckboxStateUseCase.setBuildingDetailCheckboxState(id,state)
    }


}