package com.myproject.campusmap_cleanarchitecture.ui.bus

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myproject.campusmap_cleanarchitecture.domain.error.NetworkResult
import com.myproject.campusmap_cleanarchitecture.domain.model.BusPosition
import com.myproject.campusmap_cleanarchitecture.domain.usecase.GetBusPositionDataUseCase
import com.myproject.campusmap_cleanarchitecture.ui.util.Event
import com.myproject.campusmap_cleanarchitecture.ui.util.toErrorMessage
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BusPositionViewModel @Inject constructor(
    private val application: Application,
    private val useCase: GetBusPositionDataUseCase
) : ViewModel() {

    private val _busPositionDatas = MutableLiveData<List<BusPosition>?>()
    val busPositionDatas : LiveData<List<BusPosition>?>
        get() = _busPositionDatas

    private val _showToast: MutableLiveData<Event<String>> = MutableLiveData()
    val showToast: LiveData<Event<String>> = _showToast

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(true)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isError: MutableLiveData<Boolean> = MutableLiveData()
    val isError: LiveData<Boolean> = _isError


    fun getBusPositionData(stopStdid: Int) {

        viewModelScope.launch {
            _isLoading.postValue(true)
            when (val result = useCase.invoke(stopStdid)) {
                is NetworkResult.Success -> {
                    _busPositionDatas.value = result.data
                    _isLoading.postValue(false)
                    _isError.postValue(false)
                }
                is NetworkResult.Error -> {
                    val msg = result.errorType.toErrorMessage(getApplication(application).applicationContext)
                    _showToast.value = Event(msg)
                    _isLoading.postValue(false)
                    _isError.postValue(true)
                }
            }
        }
/*        viewModelScope.launch {
           _busPositionDatas.value = useCase.invoke(stopStdid)
        }*/
    }

}