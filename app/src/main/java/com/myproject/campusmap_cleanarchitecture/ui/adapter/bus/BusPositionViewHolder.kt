package com.myproject.campusmap_cleanarchitecture.ui.adapter.bus

import androidx.recyclerview.widget.RecyclerView
import com.myproject.campusmap_cleanarchitecture.databinding.BusPositionListItemBinding
import com.myproject.campusmap_cleanarchitecture.domain.model.BusPosition

class BusPositionViewHolder(
    private val binding: BusPositionListItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(busPosition: BusPosition) {

        binding.busData = busPosition

    }

}