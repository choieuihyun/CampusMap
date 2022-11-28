package com.myproject.campusmap_cleanarchitecture.ui.adapter.search

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.myproject.campusmap_cleanarchitecture.databinding.SearchFragmentBuildingHistoryListItemBinding
import com.myproject.campusmap_cleanarchitecture.databinding.SearchFragmentBuildingListItemBinding
import com.myproject.campusmap_cleanarchitecture.domain.model.BuildingHistory

class SearchHistoriesViewHolder(
    private val binding: SearchFragmentBuildingHistoryListItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    private var onButtonClickListener : ((BuildingHistory) -> Unit)? = null

    fun setOnButtonClickListener(listener : (BuildingHistory) -> Unit) {
        onButtonClickListener = listener
    }

    fun bind(buildingHistories: BuildingHistory) {

        binding.buildingHistory = buildingHistories

        binding.searchFloatingActionButton.setOnClickListener {
            onButtonClickListener?.let {
                it(buildingHistories)
            }
        }

    }

}