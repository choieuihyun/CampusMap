package com.myproject.campusmap_cleanarchitecture.presentation.building.buildingfavorite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.myproject.campusmap_cleanarchitecture.presentation.BaseFragment
import com.myproject.campusmap_cleanarchitecture.presentation.R
import com.myproject.campusmap_cleanarchitecture.presentation.adapter.buildingfavorite.BuildingFavoriteAdapter
import com.myproject.campusmap_cleanarchitecture.presentation.databinding.BuildingFragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BuildingFavoriteFragment : BaseFragment<BuildingFragmentFavoriteBinding>(R.layout.building_fragment_favorite) {

    private lateinit var buildingFavoriteAdapter: BuildingFavoriteAdapter

    private val viewModel: BuildingFavoriteViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        updateList()

    }

    private fun updateList() {
        viewModel.getBuildingFavorites.observe(viewLifecycleOwner) {
            buildingFavorite -> buildingFavoriteAdapter.submitList(buildingFavorite)
        }
    }

    private fun setupRecyclerView() {

        buildingFavoriteAdapter = BuildingFavoriteAdapter(requireContext(), viewModel)
        binding.buildingFavoriteRcv.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = buildingFavoriteAdapter
        }

        buildingFavoriteAdapter.setOnButtonClickListener {
            buildingFavorite -> val action = BuildingFavoriteFragmentDirections.actionBuildingFavoriteFragmentToBuildingDetailFragment(building = null, buildingHistory = null, buildingFavorite)
            findNavController().navigate(action)
        }


    }

}