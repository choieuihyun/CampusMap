package com.myproject.campusmap_cleanarchitecture.domain.usecase

import android.app.Activity
import android.widget.ImageView
import com.myproject.campusmap_cleanarchitecture.domain.repository.BuildingRepository
import javax.inject.Inject

class GetBuildingImagesUseCase @Inject constructor(
    private val repository: BuildingRepository
){

    operator fun invoke(c: Activity, path: String, v: ImageView) {
        repository.getBuildingImages(c, path, v)
    }

}