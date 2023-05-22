package com.myproject.campusmap_cleanarchitecture.presentation.adapter.bus

import androidx.recyclerview.widget.RecyclerView
import com.myproject.campusmap_cleanarchitecture.domain.model.BusPosition
import com.myproject.campusmap_cleanarchitecture.presentation.databinding.BusPositionListItemBinding

class BusPositionViewHolder(
    private val binding: BusPositionListItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(busPosition: BusPosition) {

        binding.busData = busPosition

    }

}