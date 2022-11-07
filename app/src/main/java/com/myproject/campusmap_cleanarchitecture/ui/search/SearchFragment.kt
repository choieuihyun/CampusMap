package com.myproject.campusmap_cleanarchitecture.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.myproject.campusmap_cleanarchitecture.R
import com.myproject.campusmap_cleanarchitecture.databinding.SearchFragmentBinding
import com.myproject.campusmap_cleanarchitecture.domain.model.Building
import com.myproject.campusmap_cleanarchitecture.domain.model.LectureRoom
import com.myproject.campusmap_cleanarchitecture.ui.BaseFragment
import com.myproject.campusmap_cleanarchitecture.ui.adapter.search.SearchBuildingAdapter
import com.myproject.campusmap_cleanarchitecture.ui.adapter.search.SearchLectureRoomAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<SearchFragmentBinding>(R.layout.search_fragment) {

    private lateinit var searchBuildingAdapter: SearchBuildingAdapter
    private lateinit var searchLectureRoomAdapter: SearchLectureRoomAdapter
    private var searchBuildingArray: ArrayList<Building> = ArrayList()
    private var searchLectureRoomArray: ArrayList<LectureRoom> = ArrayList()

    private val viewModel: SearchFragmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        binding.searchRecyclerView.apply {

            setHasFixedSize(true)

            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))

        }

        }

    private fun setupRecyclerView() {

        searchBuildingAdapter = SearchBuildingAdapter()
        searchLectureRoomAdapter = SearchLectureRoomAdapter()

        binding.searchBuilding.setOnClickListener {

            binding.searchRecyclerView.adapter = searchBuildingAdapter

            it.isSelected = true
            binding.searchLectureRoom.isSelected = false

            viewModel.getBuildings.observe(viewLifecycleOwner) { buildings -> run {

                searchBuildingAdapter.submitList(buildings)

                binding.buildingSearchEdittext.addTextChangedListener(object : TextWatcher {

                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int,
                    ) {

                    }

                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int,
                    ) {

                    }

                    override fun afterTextChanged(s: Editable?) {

                        val searchText = binding.buildingSearchEdittext.text.toString()
                        searchBuildingArray.clear()

                        if (searchText != "") {
                            for (i in buildings.indices) {
                                if (buildings[i].name.contains(searchText)) {
                                    searchBuildingArray.add(buildings[i])
                                }
                            }
                        }
                    }
                })
                binding.buildingSearchButton.setOnClickListener {
                    searchBuildingAdapter.submitList(searchBuildingArray.toMutableList())
                }
            }
            }
        }

        binding.searchLectureRoom.setOnClickListener {

            binding.searchRecyclerView.adapter = searchLectureRoomAdapter

            it.isSelected = true
            binding.searchBuilding.isSelected = false

            viewModel.getLectureRooms.observe(viewLifecycleOwner) { lectureRooms -> run {

                searchLectureRoomAdapter.submitList(lectureRooms)

                binding.buildingSearchEdittext.addTextChangedListener(object : TextWatcher {

                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int,
                    ) {

                    }

                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int,
                    ) {

                    }

                    override fun afterTextChanged(s: Editable?) {
                        val searchText = binding.buildingSearchEdittext.text.toString()
                        searchLectureRoomArray.clear()

                        if (searchText != "") {
                            for (i in lectureRooms.indices) {
                                if (lectureRooms[i].name?.contains(searchText) == true) {
                                    searchLectureRoomArray.add(lectureRooms[i])
                                }
                            }
                        }
                    }

                })
                binding.buildingSearchButton.setOnClickListener {
                    searchLectureRoomAdapter.submitList(searchLectureRoomArray.toMutableList())
                }
            }
            }
        }

        searchBuildingAdapter.setOnItemClickListener {
            building ->
            val action = SearchFragmentDirections.actionSearchFragmentToMapFragment(building)
            findNavController().navigate(action)
        }

/*        searchLectureRoomAdapter.setOnItemClickListener {
            lectureRoom ->
            val action = SearchFragmentDirections.actionSearchFragmentToMapFragment(lectureRoom)
            findNavController().navigate(action)
        }*/
    }
}


