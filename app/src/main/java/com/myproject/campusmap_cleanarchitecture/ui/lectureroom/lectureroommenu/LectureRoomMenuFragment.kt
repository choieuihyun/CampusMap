package com.myproject.campusmap_cleanarchitecture.ui.lectureroom.lectureroommenu

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.myproject.campusmap_cleanarchitecture.R
import com.myproject.campusmap_cleanarchitecture.databinding.LectureroomFragmentMenuBinding
import com.myproject.campusmap_cleanarchitecture.domain.model.Building
import com.myproject.campusmap_cleanarchitecture.ui.BaseFragment
import com.myproject.campusmap_cleanarchitecture.ui.adapter.lectureroommenu.LectureRoomMenuAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LectureRoomMenuFragment : BaseFragment<LectureroomFragmentMenuBinding>(R.layout.lectureroom_fragment_menu) {

    private lateinit var lectureRoomAdapter: LectureRoomMenuAdapter
    private lateinit var buttonArgs : ArrayList<AppCompatButton>

    private val viewModel: LectureRoomMenuViewModel by viewModels()
    private val args by navArgs<LectureRoomMenuFragmentArgs>() // 제거해야할듯.

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lectureRoomMenuViewModel = viewModel

        setupRecyclerView()

        updateBuildingName()

            binding.lectureRoomMenuBtn1.setOnClickListener {
                changeSelected(0)
                it.isSelected = true
                updateLectureRoomList(floor = 1)
            }

            binding.lectureRoomMenuBtn2.setOnClickListener {
                changeSelected(1)
                it.isSelected = true
                updateLectureRoomList(floor = 2)
            }

            binding.lectureRoomMenuBtn3.setOnClickListener {
                changeSelected(2)
                it.isSelected = true
                updateLectureRoomList(floor = 3)
            }

            binding.lectureRoomMenuBtn4.setOnClickListener {
                changeSelected(3)
                it.isSelected = true
                updateLectureRoomList(floor = 4)
            }

            binding.lectureRoomMenuBtn5.setOnClickListener {
                changeSelected(4)
                it.isSelected = true
                updateLectureRoomList(floor = 5)
            }

            binding.lectureRoomMenuBtn6.setOnClickListener {
                changeSelected(5)
                it.isSelected = true
                updateLectureRoomList(floor = 6)
            }
    }

    private fun changeSelected(index: Int) {

        buttonArgs = ArrayList()
        buttonArgs.add(binding.lectureRoomMenuBtn1)
        buttonArgs.add(binding.lectureRoomMenuBtn2)
        buttonArgs.add(binding.lectureRoomMenuBtn3)
        buttonArgs.add(binding.lectureRoomMenuBtn4)
        buttonArgs.add(binding.lectureRoomMenuBtn5)
        buttonArgs.add(binding.lectureRoomMenuBtn6)

        buttonArgs.removeAt(index)

        for(i in 0 until buttonArgs.size) {
            buttonArgs[i].isSelected = false
        }
    }

    private fun updateBuildingName() {

        if(args.building != null) {

            viewModel.getLectureRoomDetailData(args.building!!)

        } else if (args.buildingHistory != null) {

            viewModel.getLectureRoomDetailData(args.buildingHistory!!)

        }
    }

    private fun updateLectureRoomList(floor: Int) {

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


