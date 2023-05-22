package com.myproject.campusmap_cleanarchitecture.presentation.building.buildingmenu

import androidx.lifecycle.ViewModel
import com.myproject.campusmap_cleanarchitecture.domain.usecase.GetBuildingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BuildingMenuViewModel @Inject constructor(
    private val buildingsUseCase: GetBuildingsUseCase
) : ViewModel() {

/*    private val _buildings: MutableLiveData<List<Building>> = MutableLiveData()
    val buildings: LiveData<List<Building>> get() = _buildings*/

    val getBuildings = buildingsUseCase.invoke()


/*    private val buildingRepository = BuildingRepository.get()
    val buildingsLiveData = buildingRepository.getBuildings()*/

}