package com.myproject.campusmap_cleanarchitecture.ui.adapter.search

import androidx.recyclerview.widget.RecyclerView
import com.myproject.campusmap_cleanarchitecture.databinding.SearchFragmentBuildingListItemBinding
import com.myproject.campusmap_cleanarchitecture.domain.model.Building

class SearchBuildingViewHolder(
    private val binding: SearchFragmentBuildingListItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(building: Building) {

        binding.building = building

    }

}