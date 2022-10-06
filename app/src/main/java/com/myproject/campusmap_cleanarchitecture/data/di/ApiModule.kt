package com.campusmap.android.campusmap_with_kakao.data.di

import com.campusmap.android.campusmap_with_kakao.data.db.remote.api.BusApi
import retrofit2.Retrofit

object ApiModule { // 이걸 왜 이렇게 했는지를 생각해라.

    fun busApi(retrofit: Retrofit): BusApi {
        return retrofit.create(BusApi::class.java)
    }

}