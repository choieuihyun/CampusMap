package com.myproject.campusmap_cleanarchitecture.ui.bus

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myproject.campusmap_cleanarchitecture.domain.model.BusStop
import com.myproject.campusmap_cleanarchitecture.domain.usecase.GetBusStopDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BusStopViewModel @Inject constructor(
    private val useCase: GetBusStopDataUseCase
) : ViewModel() {

    private val _busStopDatas = MutableLiveData<List<BusStop>>()
    val busStopDatas : LiveData<List<BusStop>>
        get() = _busStopDatas

    fun getBusStopData() {

        viewModelScope.launch {
            val busStopArray = mutableListOf<BusStop>()

            // 승강장 정보는 시간에 따라 달라지는게 아니라 무조건 있음. 버스 운행 정보가 시간에 따라 null이 결정됨.
            busStopArray.add(useCase.invoke("36628", "종점 방면")!!)
            busStopArray.add(useCase.invoke("36629", "교수연구동 방면")!!)
            busStopArray.add(useCase.invoke("36630", "전주대 캠퍼스 방면")!!)
            busStopArray.add(useCase.invoke("36631", "호남제일고 방면")!!)

            _busStopDatas.value = busStopArray
        }
    }


}