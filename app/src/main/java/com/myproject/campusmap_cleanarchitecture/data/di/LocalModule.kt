package com.myproject.campusmap_cleanarchitecture.data.di

import android.content.Context
import androidx.room.PrimaryKey
import androidx.room.Room
import com.myproject.campusmap_cleanarchitecture.data.db.local.database.BuildingDatabase
import com.myproject.campusmap_cleanarchitecture.data.db.local.database.BuildingFavoriteDatabase
import com.myproject.campusmap_cleanarchitecture.data.db.local.database.BuildingHistoriesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    private const val DATABASE_NAME = "building.db"

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) : BuildingDatabase =
            Room.databaseBuilder(context,
                BuildingDatabase::class.java,
                DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .createFromAsset("databases/building.db")
                .build()

    @Singleton
    @Provides
    fun provideHistoriesDatabase(@ApplicationContext context: Context) : BuildingHistoriesDatabase =
        Room.databaseBuilder(context,
            BuildingHistoriesDatabase::class.java,
            "historiesDB")
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideFavoriteDatabase(@ApplicationContext context: Context) : BuildingFavoriteDatabase =
        Room.databaseBuilder(context,
        BuildingFavoriteDatabase::class.java,
        "favoritesDB")
            .build()

}