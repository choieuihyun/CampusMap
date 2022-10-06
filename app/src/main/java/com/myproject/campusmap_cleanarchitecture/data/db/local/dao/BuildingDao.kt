package com.campusmap.android.campusmap_with_kakao.data.db.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.campusmap.android.campusmap_with_kakao.data.db.local.entity.BuildingEntity
import com.campusmap.android.campusmap_with_kakao.data.db.local.entity.EngFirstLectureRoomEntity

@Dao
interface BuildingDao {

    // 쿼리에서 LiveData를 반환하도록 구성하면, Room이 백그라운드 스레드에서 쿼리 작업을 자동 실행한 후 그 결과를 LiveData 객체로 반환한다.
    // 따라서 액티비티나 프래그먼트에서는 LiveData 객체를 관찰하도록 설정만 하면 된다, 그리고 LiveData 객체가 준비되면 main 스레드의 액티비티나 프래그먼트에 통보됨.
    // 쿼리문을 제외한 CRUD문은 시간이 걸리는 작업이기때문에 suspend를 사용함. 여긴 다 쿼리니까 ㄱㅊ.
    @Query("SELECT * FROM building")
    fun getBuildings(): LiveData<List<BuildingEntity>>

    @Query("SELECT * FROM building WHERE id =(:id)")
    fun getBuilding(id : Int): LiveData<BuildingEntity?>

    // 공학1관 Dao
    @Query("SELECT * FROM EngFirstLectureRoomEntity")
    fun getLectureRooms(): LiveData<List<EngFirstLectureRoomEntity>>

    @Query("SELECT * FROM EngFirstLectureRoomEntity WHERE id = (:id)")
    fun getLectureRoom(id : Int): LiveData<EngFirstLectureRoomEntity?>

}