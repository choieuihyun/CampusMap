package com.myproject.campusmap_cleanarchitecture.presentation.lectureroom.lectureroomdetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.myproject.campusmap_cleanarchitecture.presentation.BaseFragment
import com.myproject.campusmap_cleanarchitecture.presentation.R
import com.myproject.campusmap_cleanarchitecture.presentation.adapter.lectureroomdetail.LectureRoomDetailViewPagerAdapter
import com.myproject.campusmap_cleanarchitecture.presentation.databinding.LectureroomFragmentDetailBinding
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









