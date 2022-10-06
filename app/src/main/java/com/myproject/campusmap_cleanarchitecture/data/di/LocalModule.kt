package com.campusmap.android.campusmap_with_kakao.data.di

import androidx.room.Room
import com.campusmap.android.campusmap_with_kakao.data.db.local.database.BuildingDatabase

object LocalModule {

    // DB 이름에 대문자 쓰면 안되나본데??
    private const val DATABASE_NAME = "building.db"

    // db 생성, 여기서 데이터를 액세스하는 로직을 캡슐화하고 뭐 저장하고 하니까 DB생성을 여기서 하는거지.
    private val database : BuildingDatabase = Room.databaseBuilder(
        context.applicationContext,
        BuildingDatabase::class.java,
        DATABASE_NAME
        // 이 fromAsset이 ㅈㄴ 웃긴게 왼쪽에 assets에만 넣어주는게 아니라 Device File Explorer에 넣어줘야 db를 받았다고함. 이러면 말이 애매하잖아 뭐야 이게.
    ).createFromAsset("databases/buildings.db").build()

    // Dao 참조
    fun provideBuildingDao(database: BuildingDatabase) = database.buildingDao()
}