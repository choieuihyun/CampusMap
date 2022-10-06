package com.campusmap.android.campusmap_with_kakao.lectureroom

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.campusmap.android.campusmap_with_kakao.R
import com.campusmap.android.campusmap_with_kakao.data.db.local.entity.EngFirstLectureRoomEntity
import com.campusmap.android.campusmap_with_kakao.databinding.BuildingLectureRoomDetailFragmentBinding
import com.campusmap.android.campusmap_with_kakao.firebase.ConnectFirebase
import com.myproject.campusmap_cleanarchitecture.ui.main.MainActivity
import java.io.Serializable

private const val TAG = "BuildingLectureDetail"

class BuildingLectureRoomDetail: Fragment() {

    private lateinit var binding: BuildingLectureRoomDetailFragmentBinding

    private lateinit var viewpager: ViewPager2

    private lateinit var cf: ConnectFirebase

    private lateinit var lectureRoom: EngFirstLectureRoomEntity
    private lateinit var lectureRoomId: Serializable

    //private var adapter: ViewPagerAdapter? = ViewPagerAdapter(emptyList())
    private lateinit var adapter: ViewPagerAdapter

    private lateinit var mainActivity: MainActivity

    private val lectureRoomViewModel: LectureRoomMenuViewModel by lazy {
        ViewModelProvider(this).get(LectureRoomMenuViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lectureRoomId = arguments?.getSerializable("lectureRoomId")!!
        lectureRoom = EngFirstLectureRoomEntity(lectureRoomId as Int)

        Log.d(TAG, lectureRoom.toString())

        cf = ConnectFirebase()

        adapter = ViewPagerAdapter(requireActivity())

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.building_lecture_room_detail_fragment,
            container,
            false
        )

        viewpager = binding.viewpager // viewPager 인스턴스에 xml의 viewPager 넣어주고
        //viewpager.adapter = ViewPagerAdapter(ArrayList(), viewPagerItem)
        viewpager.adapter = adapter // viewPager 어댑터에 viewPagerAdapter 인스턴스 넣어주고
        //adapter = ViewPagerAdapter(ArrayList(), viewPagerItem)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lectureRoomViewModel.buildingLectureRoomLiveData.observe(
            viewLifecycleOwner
        ) { lectureRoomDetail ->
            lectureRoomDetail.let {
                this.lectureRoom =
                    lectureRoomDetail[lectureRoom.id - 1] // 요건 당연하다. 조영태 교수는 저 배열에서 0번째거든.
                Log.d(TAG, this.lectureRoom.toString())
                Log.d(TAG, this.lectureRoom.route_1.toString())
                Log.d(TAG, this.lectureRoom.route_2.toString())
                Log.d(TAG, this.lectureRoom.route_3.toString())
                updateViewPager(it)
            }
        }
    }

    private inner class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {

        override fun getItemCount(): Int {
            return 3
        }

        override fun createFragment(position: Int): Fragment {

            return when (position) {
                0 -> LectureRoomDetailViewPager1.newInstance(lectureRoom.id-1)
                1 -> LectureRoomDetailViewPager2.newInstance(lectureRoom.id-1)
                else -> LectureRoomDetailViewPager3.newInstance(lectureRoom.id-1)
            }

        }

    }

/*
    private inner class ViewPagerAdapter(var lectureRoomList: List<Eng1LectureRoom>) :
        RecyclerView.Adapter<ViewPagerHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerHolder {
            val view = layoutInflater.inflate(
                R.layout.building_lecture_room_detail_viewpager_item,
                parent,
                false
            )
            return ViewPagerHolder(view)
        }

        override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) {
            val viewPagerImage = lectureRoomList[position]
            holder.bind(viewPagerImage)
        }

        override fun getItemCount(): Int {
            return lectureRoomList.size-138 // 일단 이렇게 해두는데 이건 너무 하드코딩이라 다음에 생각해보자.
        }

    }


        // Holder에서 bind할꺼니까 여기서 데이터 어떻게 받을껀지 설정을 해야지.
        private inner class ViewPagerHolder(view: View) : RecyclerView.ViewHolder(view) {

            private lateinit var lectureRoom: Eng1LectureRoom
            private lateinit var viewPager: ImageView
            //val viewPager: ImageView = itemView.findViewById(R.id.viewpager_item)

            fun bind(lectureRoom: Eng1LectureRoom) {
                this.lectureRoom = lectureRoom
                viewPager = itemView.findViewById(R.id.viewpager_item)
                val viewPagerList: ArrayList<Unit> = ArrayList()
*/
/*                        cf.connect(
                            mainActivity,
                            this.lectureRoom.route_2!!,
                            viewPager)*//*


                        */
/*viewPagerList.add(
                            cf.connect3(this@BuildingLectureRoomDetail,
                                this.lectureRoom.route_1!!,
                                viewPager
                            )
                        )*//*

*/
/*                viewPager.let {

                }*//*


                }
            }
*/



    private fun updateViewPager(lectureRoomList: List<EngFirstLectureRoomEntity>) {
        adapter = ViewPagerAdapter(requireActivity()) // 여기서 그 firebase넣고 imageView 넣고
        viewpager.adapter = adapter
    }

    companion object {
        fun newInstance(lectureRoomId: Int): BuildingLectureRoomDetail {
            val args = Bundle().apply {
                putSerializable("lectureRoomId", lectureRoomId)
            }
            return BuildingLectureRoomDetail().apply {
                arguments = args
            }
        }
    }
}









