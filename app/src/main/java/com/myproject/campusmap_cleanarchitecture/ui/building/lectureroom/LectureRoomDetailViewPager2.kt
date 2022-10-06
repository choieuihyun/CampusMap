package com.campusmap.android.campusmap_with_kakao.lectureroom

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.campusmap.android.campusmap_with_kakao.R
import com.campusmap.android.campusmap_with_kakao.data.db.entity.Eng1LectureRoom
import com.campusmap.android.campusmap_with_kakao.firebase.ConnectFirebase
import java.io.Serializable

class LectureRoomDetailViewPager2 : Fragment(){

    private lateinit var cf: ConnectFirebase
    private lateinit var lectureRoom: Eng1LectureRoom
    private lateinit var lectureRoomId: Serializable

    private val lectureRoomViewModel : LectureRoomMenuViewModel by lazy {
        ViewModelProvider(this).get(LectureRoomMenuViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lectureRoomId = arguments?.getSerializable("id")!!
        lectureRoom = Eng1LectureRoom(lectureRoomId as Int)

        cf = ConnectFirebase()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.building_lecture_room_detail_viewpager_item_2, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageView = view.findViewById<ImageView>(R.id.viewpager_item_2)
        lectureRoomViewModel.buildingLectureRoomLiveData.observe(
            viewLifecycleOwner, {
                    lectureRoom -> lectureRoom.let {
                        this.lectureRoom = lectureRoom[lectureRoomId as Int]
                        Log.d("lectureRoom.route_1", this.lectureRoom.route_2.toString())
                        cf.connect(requireActivity(), this.lectureRoom.route_2!!, imageView)
                }
            }
        )
    }

    companion object {
        fun newInstance(id: Int) : LectureRoomDetailViewPager2 {
            val args = Bundle().apply {
                putSerializable("id", id)
            }
            return LectureRoomDetailViewPager2().apply {
                arguments = args
            }
        }
    }

}