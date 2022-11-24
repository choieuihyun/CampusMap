package com.myproject.campusmap_cleanarchitecture.ui.adapter.search

import androidx.recyclerview.widget.RecyclerView
import com.myproject.campusmap_cleanarchitecture.databinding.SearchFragmentBuildingHistoryListItemBinding
import com.myproject.campusmap_cleanarchitecture.databinding.SearchFragmentBuildingListItemBinding
import com.myproject.campusmap_cleanarchitecture.domain.model.BuildingHistory

class SearchHistoriesViewHolder(
    private val binding: SearchFragmentBuildingHistoryListItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(buildingHistories: BuildingHistory) {
        binding.buildingHistory = buildingHistories
    }

}