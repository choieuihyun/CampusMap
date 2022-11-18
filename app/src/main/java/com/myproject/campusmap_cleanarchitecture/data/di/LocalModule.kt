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

}