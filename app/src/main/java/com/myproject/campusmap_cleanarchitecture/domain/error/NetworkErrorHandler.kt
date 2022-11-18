package com.myproject.campusmap_cleanarchitecture.domain.error

interface NetworkErrorHandler {

    fun getError(exception: Throwable): NetworkError

}