package com.myproject.campusmap_cleanarchitecture.domain.repository

import androidx.lifecycle.LiveData
import com.campusmap.android.campusmap_with_kakao.data.db.local.entity.BuildingEntity
import com.myproject.campusmap_cleanarchitecture.domain.model.Building

interface BuildingRepository {

    // 이거 return 타입이 BuildingEntity가 아니라 Building이어야 하는거 아닌가?
    // ViewModel에선 Mapper에서 Model로 변환된거 쓰니까.
    // getBuilding은 Livedata일 필요가 없지 않을까?
    fun getBuildings(): LiveData<List<Building>>

    fun getBuilding(id: Int): LiveData<Building>


}