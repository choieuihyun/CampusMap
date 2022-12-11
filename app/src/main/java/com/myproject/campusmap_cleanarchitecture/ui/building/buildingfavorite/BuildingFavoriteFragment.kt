package com.myproject.campusmap_cleanarchitecture.ui.building.buildingfavorite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.myproject.campusmap_cleanarchitecture.R
import com.myproject.campusmap_cleanarchitecture.databinding.BuildingFragmentFavoriteBinding
import com.myproject.campusmap_cleanarchitecture.ui.BaseFragment
import com.myproject.campusmap_cleanarchitecture.ui.adapter.buildingfavorite.BuildingFavoriteAdapter
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


    }

}