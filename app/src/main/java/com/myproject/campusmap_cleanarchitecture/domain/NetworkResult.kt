package com.myproject.campusmap_cleanarchitecture.domain

import com.myproject.campusmap_cleanarchitecture.domain.model.BusPosition

sealed class NetworkResult<out T> {

    class Success<T>(val data: T) : NetworkResult<T>()
    class Error(val errorType: NetworkError) : NetworkResult<Nothing>()
}