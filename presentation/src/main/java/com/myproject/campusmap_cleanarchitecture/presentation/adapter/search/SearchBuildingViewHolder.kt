package com.myproject.campusmap_cleanarchitecture.presentation.adapter.search

import androidx.recyclerview.widget.RecyclerView
import com.myproject.campusmap_cleanarchitecture.domain.model.Building
import com.myproject.campusmap_cleanarchitecture.presentation.databinding.SearchFragmentBuildingListItemBinding

class SearchBuildingViewHolder(
    private val binding: SearchFragmentBuildingListItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(building: Building) {

        binding.building = building

    }

}