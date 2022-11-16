package com.myproject.campusmap_cleanarchitecture.domain

sealed class NetworkError {
    object Unknown : NetworkError()
    object Timeout : NetworkError()
    object InternalServer : NetworkError()
    class BadRequest(val code: Int) : NetworkError()
}