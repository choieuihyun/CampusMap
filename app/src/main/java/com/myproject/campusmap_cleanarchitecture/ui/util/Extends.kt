package com.myproject.campusmap_cleanarchitecture.ui.util

import android.content.Context
import android.widget.Toast
import com.myproject.campusmap_cleanarchitecture.R
import com.myproject.campusmap_cleanarchitecture.domain.error.NetworkError
import com.myproject.campusmap_cleanarchitecture.domain.error.RoomError

fun NetworkError.toErrorMessage(
    context: Context
): String {

    return when (this) {
        is NetworkError.Unknown -> {
            context.getString(R.string.error_unknown)
        }
        is NetworkError.BadRequest -> {
            context.getString(R.string.error_bad_request, code)
        }
        is NetworkError.Timeout -> {
            context.getString(R.string.error_timeout)
        }
        is NetworkError.InternalServer -> {
            context.getString(R.string.error_internal_server)
        }

    }
}

fun RoomError.toErrorMessage(
    context: Context
) : String {

    return when (this) {
        is RoomError.Unknown -> {
            context.getString(R.string.room_error_msg)
        }
    }

}