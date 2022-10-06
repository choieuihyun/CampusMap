package com.campusmap.android.campusmap_with_kakao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.campusmap.android.campusmap_with_kakao.building.Building

// 이거 만들 필요가 없는거 아닌가? MapFragmentDetailViewModel이랑 어차피 앱 켜진동안 계속 같이 유지되는데
// 그 Data를 그대로 갖다쓰면 되는거잖아. 그거 그대로 관찰해서 뷰 갱신해주면 되는거고.
// 정리하자면 BuildingDetailFragment 인스턴스가 생성될 때 onCreate에서
// MapFragmentViewModel.loadBuliding()으로 한번 더 호출해주면 되는거 아니냐?
// 아니면 호출 안해도 앱 생명주기 내에 계속 유지되고 있으니까 그냥 쓴다던지.
// 이거 생성하면 ViewModel이 두개잖아. 같은 데이터를 쓸텐데 굳이 두개를 만드냐 이거지. CriminalIntent는 다른 데이터니까 그러고
// 일단 만들어보자.
class BuildingDetailViewModel : ViewModel() {

    private val buildingRepository = BuildingRepository.get()
    private val buildingIdLiveData = MutableLiveData<Int>()

    var buildingLiveData : LiveData<Building?> =
        Transformations.switchMap(buildingIdLiveData) { buildingId ->
            buildingRepository.getBuilding(buildingId)
        }

    fun loadBuilding(buildingId: Int) {
        buildingIdLiveData.value = buildingId
    }
}