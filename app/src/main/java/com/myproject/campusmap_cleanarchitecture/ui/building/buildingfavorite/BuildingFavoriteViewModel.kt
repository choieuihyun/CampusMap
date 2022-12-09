package com.myproject.campusmap_cleanarchitecture.ui.building.buildingfavorite

import androidx.lifecycle.ViewModel
import com.myproject.campusmap_cleanarchitecture.domain.usecase.AddBuildingFavoritesUseCase
import com.myproject.campusmap_cleanarchitecture.domain.usecase.DeleteBuildingFavoritesUseCase
import com.myproject.campusmap_cleanarchitecture.domain.usecase.GetBuildingFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BuildingFavoriteViewModel @Inject constructor(
    private val getBuildingFavoriteUseCase : GetBuildingFavoritesUseCase
) : ViewModel() {

    val getBuildingFavorites = getBuildingFavoriteUseCase.invoke()


}