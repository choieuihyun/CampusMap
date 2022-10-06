package com.campusmap.android.campusmap_with_kakao.lectureroom

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.campusmap.android.campusmap_with_kakao.MapFragmentViewModel
import com.campusmap.android.campusmap_with_kakao.R
import com.campusmap.android.campusmap_with_kakao.building.Building
import com.campusmap.android.campusmap_with_kakao.data.db.entity.Eng1LectureRoom
import com.campusmap.android.campusmap_with_kakao.databinding.BuildingLectureRoomMenuFragmentBinding
import java.io.Serializable

private const val TAG = "BuildingLectureRoomMenu"

class BuildingLectureRoomMenu : Fragment() {

    private lateinit var binding: BuildingLectureRoomMenuFragmentBinding
    private lateinit var lectureRoomRecyclerView: RecyclerView
    // 요걸로 buildingId 받아와서 ( ) 정보보기 title 설정
    private lateinit var buildingId: Serializable
    private lateinit var building: Building
    private var adapter: LectureRoomMenuAdapter? = LectureRoomMenuAdapter(emptyList())

    private var callbacks: Callbacks? = null

    private val lectureRoomViewModel: LectureRoomMenuViewModel by lazy {
        ViewModelProvider(this).get(LectureRoomMenuViewModel::class.java)
    }

    private val buildingViewModel: MapFragmentViewModel by lazy {
        ViewModelProvider(this).get(MapFragmentViewModel::class.java)
    }

    interface Callbacks {
        fun onLectureRoomClicked(lectureRoomId: Int)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        buildingId = arguments?.getSerializable("buildingId")!!

        building = Building(buildingId as Int)

        // 요걸 빼먹으면 안되제..
        buildingViewModel.loadBuilding(buildingId as Int)

        // 헉 이거 찍어보니까 구조가 존나 헷갈린다.
        // 필요한 데이터만 받아서 쓰면 되는건가? 이게 여기서 데이터를 생성할 순 없잖아. 이미 ViewModel에 있는걸 들고오는거지 + 여긴 UI변경만 담당해야하고
        // 그리고 애초에 구조 자체가 Fragment들이 이어져서 계속 안으로 들어가는 구조니까 이렇게 하는게 맞는것 같은데 누가 나 좀 알려줘라..ㅠㅠㅠㅠㅠㅠㅠㅠ
        Log.d(TAG, building.toString())

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,
            R.layout.building_lecture_room_menu_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lectureRoomRecyclerView = view.findViewById(R.id.lectureRoom_RecyclerView) as RecyclerView
        lectureRoomRecyclerView.layoutManager = LinearLayoutManager(context)
        lectureRoomRecyclerView.adapter = adapter

/*        binding.menuBtn1.setOnClickListener {
            lectureRoomViewModel.buildingLectureRoomLiveData.observe(
                viewLifecycleOwner,
                { lectureRooms ->
                    lectureRooms.let {
                        updateView(lectureRooms.filter {
                            it.floor == 1
                        }) // 와 이걸 viewHolder에서만 해결하려 했는데 걍 여기서 짤라도 됐음.
                    }
                }
            )
        }*/

        binding.run {
            menuBtn1.setOnClickListener {
                lectureRoomViewModel.buildingLectureRoomLiveData.observe(
                    viewLifecycleOwner,
                    { lectureRooms ->
                        lectureRooms.let {
                            updateView(lectureRooms.filter {
                                it.floor == 1
                            }) // 와 이걸 viewHolder에서만 해결하려 했는데 걍 여기서 짤라도 됐음.
                        }
                    }
                )
            }

            menuBtn2.setOnClickListener {
                lectureRoomViewModel.buildingLectureRoomLiveData.observe(
                    viewLifecycleOwner,
                    { lectureRooms ->
                        lectureRooms.let {
                            updateView(
                                lectureRooms.filter {
                                    it.floor == 2
                                }
                            ) // 와 이걸 viewHolder에서만 해결하려 했는데 걍 여기서 짤라도 됐음.
                        }
                    }
                )
            }

            menuBtn3.setOnClickListener {
                lectureRoomViewModel.buildingLectureRoomLiveData.observe(
                    viewLifecycleOwner,
                    { lectureRooms ->
                        lectureRooms.let {
                            updateView(
                                lectureRooms.filter {
                                    it.floor == 3
                                }
                            ) // 와 이걸 viewHolder에서만 해결하려 했는데 걍 여기서 짤라도 됐음.
                        }
                    }
                )
            }

            menuBtn4.setOnClickListener {
                lectureRoomViewModel.buildingLectureRoomLiveData.observe(
                    viewLifecycleOwner,
                    { lectureRooms ->
                        lectureRooms.let {
                            updateView(
                                lectureRooms.filter {
                                    it.floor == 4
                                }
                            ) // 와 이걸 viewHolder에서만 해결하려 했는데 걍 여기서 짤라도 됐음.
                        }
                    }
                )
            }

            menuBtn5.setOnClickListener {
                lectureRoomViewModel.buildingLectureRoomLiveData.observe(
                    viewLifecycleOwner,
                    { lectureRooms ->
                        lectureRooms.let {
                            updateView(
                                lectureRooms.filter {
                                    it.floor == 5
                                }
                            ) // 와 이걸 viewHolder에서만 해결하려 했는데 걍 여기서 짤라도 됐음.
                        }
                    }
                )
            }

            menuBtn6.setOnClickListener {
                lectureRoomViewModel.buildingLectureRoomLiveData.observe(
                    viewLifecycleOwner,
                    { lectureRooms ->
                        lectureRooms.let {
                            updateView(
                                lectureRooms.filter {
                                    it.floor == 6
                                }
                            ) // 와 이걸 viewHolder에서만 해결하려 했는데 걍 여기서 짤라도 됐음.
                        }
                    }
                )
            }

        }


        buildingViewModel.buildingLiveData.observe(
            viewLifecycleOwner,
            {
                    building ->
                building.let {
                    this.building = building
                    Log.d(TAG, this.building.name)
                    updateTitle(this.building)
                }
            }
        )

    }

    private inner class LectureRoomHolder(view: View) : RecyclerView.ViewHolder(view) , View.OnClickListener {

        private lateinit var lectureRooms: Eng1LectureRoom

        private val titleTextView: TextView = view.findViewById(R.id.lectureroom_title)

        // 계속 실행시켜줄꺼면 init에 넣으면 됨 그냥.
        // init이 생성자 느낌이잖아 잘 생각해봐.
        init {
            view.setOnClickListener(this)
        }

        fun bind(lectureRoom: Eng1LectureRoom) {
            this.lectureRooms = lectureRoom
            titleTextView.text = this.lectureRooms.name
        }

        override fun onClick(v: View?) {
            callbacks?.onLectureRoomClicked(lectureRooms.id)
        }

    }

    private inner class LectureRoomMenuAdapter(var lectureRooms: List<Eng1LectureRoom>)
        : RecyclerView.Adapter<LectureRoomHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LectureRoomHolder {
            val view = layoutInflater.inflate(R.layout.lectureroom_list_item, parent, false)
            return LectureRoomHolder(view)
        }

        override fun onBindViewHolder(holder: LectureRoomHolder, position: Int) {
                val lectureRooms = lectureRooms[position]
                holder.bind(lectureRooms)
        }

        override fun getItemCount(): Int {
            return lectureRooms.size // 이런식으로 if문으로 분기해서 하면 될듯?
        }

        override fun getItemViewType(position: Int): Int {
            return position
        }

    }

    private fun updateView(lectureRooms: List<Eng1LectureRoom>) {
        adapter = LectureRoomMenuAdapter(lectureRooms)
        lectureRoomRecyclerView.adapter = adapter
    }

    @SuppressLint("SetTextI18n")
    private fun updateTitle(building: Building) {
        binding.lectureroomTitleId.text = "  " + building.name + " 강의실 보기"
    }

    companion object {

        fun newInstance(buildingId: Int) : BuildingLectureRoomMenu {
            val args = Bundle().apply {
                putSerializable("buildingId", buildingId)
            }
            return BuildingLectureRoomMenu().apply {
                arguments = args
            }
        }
    }
}