package com.myproject.campusmap_cleanarchitecture.ui.search

import android.annotation.SuppressLint
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
import com.myproject.campusmap_cleanarchitecture.domain.model.BuildingHistory
import com.myproject.campusmap_cleanarchitecture.domain.model.BusStop
import com.myproject.campusmap_cleanarchitecture.domain.model.LectureRoom
import com.myproject.campusmap_cleanarchitecture.ui.BaseFragment
import com.myproject.campusmap_cleanarchitecture.ui.adapter.search.SearchBuildingAdapter
import com.myproject.campusmap_cleanarchitecture.ui.adapter.search.SearchHistoriesAdapter
import com.myproject.campusmap_cleanarchitecture.ui.adapter.search.SearchLectureRoomAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<SearchFragmentBinding>(R.layout.search_fragment) {

    private lateinit var searchBuildingAdapter: SearchBuildingAdapter
    private lateinit var searchHistoriesAdapter: SearchHistoriesAdapter
    private var searchBuildingArray: ArrayList<Building> = ArrayList()

    private val viewModel: SearchFragmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        setupBuildingRecyclerView()

        setupBuildingHistoriesRecyclerView()

    }

    private fun setupRecyclerView() {

        binding.searchRecyclerView.apply {

            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))


        }

    }

    private fun setupBuildingRecyclerView() {

            searchBuildingAdapter = SearchBuildingAdapter()

            binding.searchRecyclerView.adapter = searchBuildingAdapter

            viewModel.getBuildings.observe(viewLifecycleOwner) { buildings ->

                run {

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
                                    if (buildings[i].name?.contains(searchText) == true) { // 이러면 searchFragment에서 즐겨찾기 누르고 검색할 때 이상해질듯 한데
                                        searchBuildingArray.add(buildings[i])
                                    }
                                }
                            }
                        }
                    })


                    binding.searchBuilding.setOnClickListener {

                        searchBuildingAdapter = SearchBuildingAdapter()

                        binding.searchRecyclerView.adapter = searchBuildingAdapter

                        searchBuildingAdapter.submitList(buildings)

                        searchBuildingAdapter.setOnItemClickListener { building -> // 보일러 떼는 소리 폴폴~
                            viewModel.addBuildingHistories(building)
                            val action = SearchFragmentDirections.actionSearchFragmentToMapFragment(building,
                                busStop = null,
                                buildingHistory = null)
                            findNavController().navigate(action)
                        }

                    }

                    binding.buildingSearchButton.setOnClickListener {

                        searchBuildingAdapter.submitList(searchBuildingArray.toMutableList())

                    }
            }

            searchBuildingAdapter.setOnItemClickListener { building ->
                viewModel.addBuildingHistories(building)
                val action = SearchFragmentDirections.actionSearchFragmentToMapFragment(building,
                    busStop = null,
                    buildingHistory = null)
                findNavController().navigate(action)
            }

        }

    }

    private fun setupBuildingHistoriesRecyclerView() {

        binding.searchHistory.setOnClickListener {

            searchHistoriesAdapter = SearchHistoriesAdapter()

            binding.searchRecyclerView.adapter = searchHistoriesAdapter

            viewModel.getBuildingHistories.observe(viewLifecycleOwner) {
                searchHistoriesAdapter.submitList(it)
            }

            searchHistoriesAdapter.setOnButtonClickListener {
                    buildingHistory ->
                    viewModel.deleteBuildingHistories(buildingHistory.id)
            }

            searchHistoriesAdapter.setOnItemClickListener {
                buildingHistory ->
                val action = SearchFragmentDirections.actionSearchFragmentToMapFragment(building = null, busStop = null, buildingHistory)
                findNavController().navigate(action)
            }

        }

    }


}


