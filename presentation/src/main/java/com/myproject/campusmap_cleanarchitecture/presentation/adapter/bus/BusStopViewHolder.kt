package com.myproject.campusmap_cleanarchitecture.presentation.adapter.bus

import androidx.recyclerview.widget.RecyclerView
import com.myproject.campusmap_cleanarchitecture.domain.model.BusStop
import com.myproject.campusmap_cleanarchitecture.presentation.databinding.BusStopListItemBinding

class BusStopViewHolder(
    private val binding: BusStopListItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(busStop: BusStop) {
        binding.busStopData = busStop
    }

}