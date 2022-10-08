package com.myproject.campusmap_cleanarchitecture.data.di

import android.content.Context
import androidx.room.Room
import com.myproject.campusmap_cleanarchitecture.data.db.local.database.BuildingDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    // DB 이름에 대문자 쓰면 안되나본데??
    private const val DATABASE_NAME = "building.db"

    // db 생성, 여기서 데이터를 액세스하는 로직을 캡슐화하고 뭐 저장하고 하니까 DB생성을 여기서 하는거지.

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) : BuildingDatabase =
            Room.databaseBuilder(context,
                BuildingDatabase::class.java,
                DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .createFromAsset("databases/buildings.db")
                .build()



/*    private val database : BuildingDatabase = Room.databaseBuilder(
        context.applicationContext,
        BuildingDatabase::class.java,
        DATABASE_NAME
        // 이 fromAsset이 ㅈㄴ 웃긴게 왼쪽에 assets에만 넣어주는게 아니라 Device File Explorer에 넣어줘야 db를 받았다고함. 이러면 말이 애매하잖아 뭐야 이게.
    ).createFromAsset("databases/buildings.db").build()*/

    // Dao 참조 이거 필요없는거 아니야? 나 DataSource에 있잖아.
/*    @Singleton
    @Provides
    fun provideBuildingDao(database: BuildingDatabase) = database.buildingDao()*/
}