package com.myproject.campusmap_cleanarchitecture.data.di

import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.myproject.campusmap_cleanarchitecture.data.db.remote.interactor.NetworkErrorHandlerImpl
import com.myproject.campusmap_cleanarchitecture.domain.error.NetworkErrorHandler
import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BUSAPI_BASE_URL = "http://openapi.jeonju.go.kr/jeonjubus/openApi/traffic/" // 전라북도 전주시_실시간 운행정보 서비스
    private const val JSOUP_GENERAL_BASE_URL = "https://www.jj.ac.kr/jj/community/notice/gennotice.jsp?mode=list&board_no=1041&pager.offset="

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

        val parser = TikXml.Builder().exceptionOnUnreadXml(false).build() // 원하지 않는 데이터는 제외하기 위해서. 내려받은 데이터를 모두 사용한다면 추가하지 않아도 됨.
        return Retrofit.Builder()
            .addConverterFactory(TikXmlConverterFactory.create(parser))
            .client(okHttpClient)
            .baseUrl(BUSAPI_BASE_URL)
            .build()
    }

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

    @Singleton
    @Provides
    fun provideFirebaseStorageReference(firebaseStorage: FirebaseStorage) : StorageReference  { // 이건 필요없어보이는데
        return firebaseStorage.reference
    }

    @Singleton
    @Provides
    fun provideNetworkHandler(): NetworkErrorHandler {
        return NetworkErrorHandlerImpl()
    }


}