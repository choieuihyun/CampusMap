package com.myproject.campusmap_cleanarchitecture.domain

interface NetworkErrorHandler {

    fun getError(exception: Throwable): NetworkError

}