package com.myproject.campusmap_cleanarchitecture.data.di

import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import com.google.firebase.storage.FirebaseStorage
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "http://openapi.jeonju.go.kr/jeonjubus/openApi/traffic/"

    // 참고로 모듈에 SingletonComponent라고 작성을 해주었지만, Dagger-Hilt 공식 문서에서 나와 있듯이,
    // @Singleton annotation을 필수로 작성을 하라고 되어 있습니다(싱글턴으로 사용하고자 하면). 그렇게 나와있는건 못찾았는데
    // 예시 코드에는 다 그렇게 되어있더라.

    @Singleton
    @Provides
    fun provideOkHttpClient() : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .build()
    }
/*
    @Singleton
    @Provides
    fun provideFirebase(firebase: Firebase): Firebase {
        return firebase
    }*/

    @Singleton
    @Provides
    fun provideFirebase(firebase: Firebase, @ApplicationContext context: Context): FirebaseApp? {
        return firebase.initialize(context)
    }

    @Singleton
    @Provides
    fun provideFirebaseStorage() : FirebaseStorage {
        return FirebaseStorage.getInstance()
    }




}