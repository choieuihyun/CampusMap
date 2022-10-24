package com.myproject.campusmap_cleanarchitecture.ui.lectureroom.lectureroomdetail

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.myproject.campusmap_cleanarchitecture.R
import com.myproject.campusmap_cleanarchitecture.databinding.LectureroomFragmentDetailBinding
import com.myproject.campusmap_cleanarchitecture.ui.BaseFragment
import com.myproject.campusmap_cleanarchitecture.ui.adapter.LectureRoomDetailViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LectureRoomDetailFragment: BaseFragment<LectureroomFragmentDetailBinding>(R.layout.lectureroom_fragment_detail) {

    private val viewModel: LectureRoomDetailViewModel by viewModels()
    private val args by navArgs<LectureRoomDetailFragmentArgs>()
    private lateinit var viewPagerAdapter: LectureRoomDetailViewPagerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val lectureRoom = args.lectureRoom
        binding.lectureRoomDetail = lectureRoom

        val imageList = mutableListOf(
            LectureRoomDetailViewPagerItems(lectureRoom.route_1),
            LectureRoomDetailViewPagerItems(lectureRoom.route_2),
            LectureRoomDetailViewPagerItems(lectureRoom.route_3)
        )

        viewPagerAdapter = LectureRoomDetailViewPagerAdapter(requireContext(), viewModel)
        viewPagerAdapter.submitList(imageList.filter {
            it.path != null
        })
        binding.viewpager.adapter = viewPagerAdapter

    }

}









