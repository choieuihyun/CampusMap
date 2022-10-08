package com.campusmap.android.campusmap_with_kakao.data.di

import com.campusmap.android.campusmap_with_kakao.data.db.remote.api.BusApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule { // Api를 여러개 쓴다면 이렇게 해도 되겠지.

    @Singleton
    @Provides
    fun busApi(retrofit: Retrofit): BusApi {
        return retrofit.create(BusApi::class.java)
    }

}