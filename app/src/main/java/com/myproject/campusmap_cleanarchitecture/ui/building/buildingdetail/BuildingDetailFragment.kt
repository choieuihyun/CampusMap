package com.campusmap.android.campusmap_with_kakao

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.campusmap.android.campusmap_with_kakao.building.Building
import com.campusmap.android.campusmap_with_kakao.databinding.BuildingDetailFragmentBinding
import com.campusmap.android.campusmap_with_kakao.firebase.ConnectFirebase
import java.io.Serializable

private const val TAG = "BuildingDetailFragment"

class BuildingDetailFragment: Fragment() {

    private lateinit var binding: BuildingDetailFragmentBinding
    private lateinit var buildingId: Serializable
    private lateinit var building: Building
    private lateinit var mainActivity: MainActivity

    private lateinit var cf: ConnectFirebase

    private var callbacks : Callbacks? = null

    private val buildingDetailViewModel: MapFragmentViewModel by lazy {
        ViewModelProvider(this).get(MapFragmentViewModel::class.java)
    }

    interface Callbacks {
        fun onLectureRoomButtonClicked(buildingId: Int)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
        mainActivity = context as MainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        buildingId = arguments?.getSerializable("buildingId")!!
        // 만약에 데이터를 변경하는 기능이 있다거나 추가하는 기능이 있으면
        // building = Building()으로 아예 새로 만들어서 거기에 데이터를 넣는 방식으로 했을꺼야. 근데 난 그렇지 않지.
        // 근데 애초에 이렇게 매개변수 넣어서 building 인스턴스를 생성하는거부터 잘못된거 아님? Data에 View계층이 접근하잖아. 구조 박살 아닌가
        building = Building(buildingId as Int)
        buildingDetailViewModel.loadBuilding(buildingId as Int)

        Log.d(TAG, building.toString())

        cf = ConnectFirebase()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.building_detail_fragment, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buildingDetailViewModel.buildingLiveData.observe(
            viewLifecycleOwner, {
                building ->
                building.let {
                    this.building = building // 이거 안하면 안들어가네 데이터가
                    Log.d(TAG, this.building.toString())
                    updateView()
                }
            }
        )

        binding.lectureRoomButton.setOnClickListener {
            callbacks?.onLectureRoomButtonClicked(building.id)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    private fun updateView() {
        binding.buildingName.text = building.name
        // 이거 때문에 코드가 좀 더러운데 어떻게 못바꾸나 이게 맞긴 한거야? 누가 나 좀 알려줘 이렇게 하니까 빠르게 Detail 화면 껐다 켰다 하면 앱이 강종된다.
        // 뭔가 방법이 있는것 같은데 못찾는 이 더러운 기분.
        try {
            cf.connect(mainActivity, building.buildingImageUri!!, binding.buildingImage)
        } catch (e: NullPointerException) {

        }
        binding.buildingAddress.text = "효자동" // 일단 이렇게만 해놓자.
        binding.buildingCopyMachine.text = "복사기 있음"
        binding.buildingElevator.text = "엘리베이터 있음"
        binding.lectureRoomButton.text = "강의실 찾기"
    }

    companion object {
        fun newInstance(buildingId: Int): BuildingDetailFragment {
            val args = Bundle().apply {
                putSerializable("buildingId", buildingId)
            }
            return BuildingDetailFragment().apply {
                arguments = args
            }
        }
    }
}