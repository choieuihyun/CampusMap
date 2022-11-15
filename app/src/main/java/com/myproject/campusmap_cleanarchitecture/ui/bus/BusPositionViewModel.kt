package com.myproject.campusmap_cleanarchitecture.ui.bus

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myproject.campusmap_cleanarchitecture.domain.model.BusPosition
import com.myproject.campusmap_cleanarchitecture.domain.usecase.GetBusPositionDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BusPositionViewModel @Inject constructor(
    private val useCase: GetBusPositionDataUseCase
) : ViewModel() {

    private val _busPositionDatas = MutableLiveData<List<BusPosition>>()
    val busPositionDatas : LiveData<List<BusPosition>>
        get() = _busPositionDatas

    fun getBusPositionData(stopStdid: Int) {
        //val stopStdid = 305032694

        viewModelScope.launch {
           _busPositionDatas.value = useCase.invoke(stopStdid)
        }
    }

}