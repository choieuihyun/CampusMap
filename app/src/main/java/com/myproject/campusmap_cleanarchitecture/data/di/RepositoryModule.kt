package com.myproject.campusmap_cleanarchitecture.data.di

import com.myproject.campusmap_cleanarchitecture.data.datasource.localdatasource.BuildingDataSource
import com.myproject.campusmap_cleanarchitecture.data.datasource.localdatasource.LectureRoomDataSource
import com.myproject.campusmap_cleanarchitecture.data.repository.BuildingRepositoryImpl
import com.myproject.campusmap_cleanarchitecture.data.repository.LectureRoomRepositoryImpl
import com.myproject.campusmap_cleanarchitecture.domain.repository.BuildingRepository
import com.myproject.campusmap_cleanarchitecture.domain.repository.LectureRoomRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    // 이 모듈은 정확히 모르겠네 Singleton으로 Repository 생성해야해서인건 알겠는데

    /* domain 에서 DataSource 에 접근하기 위한 추상화된 인터페이스를 Repository 라고 한다.
    domain 은 자신이 받아온 data 가 서버 API 호출을 통해 가져온 것인지, 로컬 파일을 읽어온 것인지 알지 못한다.

    어떤 DataSource 를 사용할지는 프레임워크에서 결정하여 의존성 주입(Dependency Injection) 한다. 이게 Repository Module인듯?
    의존성 주입을 위한 제어권을 프레임워크에게 넘겨주기 위하여, 추상화된 인터페이스인 repository 가 필요하다.
    의존성 주입을 할 수 있는 Repository 의 실질적인 구현체는 data 모듈에서 구현하도록 한다.*/

    @Singleton
    @Provides
    fun provideBuildingRepository(dataSource: BuildingDataSource) : BuildingRepository {
        return BuildingRepositoryImpl(dataSource)
    }

    @Singleton
    @Provides
    fun provideLectureRoomRepository(dataSource: LectureRoomDataSource) : LectureRoomRepository {
        return LectureRoomRepositoryImpl(dataSource)
    }

/*    @Singleton
    @Binds
    fun provideEngFirstRepository() : EngFirstRepository {
        return EngFirstRepositoryImpl()
    }*/
}