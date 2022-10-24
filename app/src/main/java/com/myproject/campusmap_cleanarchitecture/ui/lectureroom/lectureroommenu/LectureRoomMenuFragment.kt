package com.myproject.campusmap_cleanarchitecture.ui.lectureroom.lectureroommenu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.myproject.campusmap_cleanarchitecture.R
import com.myproject.campusmap_cleanarchitecture.databinding.LectureroomFragmentMenuBinding
import com.myproject.campusmap_cleanarchitecture.ui.BaseFragment
import com.myproject.campusmap_cleanarchitecture.ui.adapter.LectureRoomMenuAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LectureRoomMenuFragment : BaseFragment<LectureroomFragmentMenuBinding>(R.layout.lectureroom_fragment_menu) {

    private lateinit var lectureRoomAdapter: LectureRoomMenuAdapter

    private val viewModel: LectureRoomMenuViewModel by viewModels()
    private val args by navArgs<LectureRoomMenuFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val lectureRoom = args.building
        binding.lectureRoomMenu = lectureRoom

        setupRecyclerView()

        binding.lectureRoomMenuBtn1.setOnClickListener {
            updateList(floor = 1)
        }

        binding.lectureRoomMenuBtn2.setOnClickListener {
            updateList(floor = 2)
        }

        binding.lectureRoomMenuBtn3.setOnClickListener {
            updateList(floor = 3)
        }

        binding.lectureRoomMenuBtn4.setOnClickListener {
            updateList(floor = 4)
        }

        binding.lectureRoomMenuBtn5.setOnClickListener {
            updateList(floor = 5)
        }

        binding.lectureRoomMenuBtn6.setOnClickListener {
            updateList(floor = 6)
        }
    }

    private fun updateList(floor: Int) {
        viewModel.getEngFirstLectureRoom.observe(viewLifecycleOwner) { lectureRooms ->
            lectureRoomAdapter.submitList(lectureRooms.filter {
                it.floor == floor
            })
        }
    }

    private fun setupRecyclerView() {
        lectureRoomAdapter = LectureRoomMenuAdapter()
        binding.lectureRoomRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(requireContext(),
                DividerItemDecoration.VERTICAL))
            adapter = lectureRoomAdapter

        }

        lectureRoomAdapter.setOnItemClickListener {
            lectureRoom ->
            val action = LectureRoomMenuFragmentDirections.actionLectureRoomMenuToLectureRoomDetailFragment(lectureRoom)
            findNavController().navigate(action)
        }
    }
}


