package com.myproject.campusmap_cleanarchitecture.domain.error

sealed class RoomError {
    object Unknown : RoomError()
}