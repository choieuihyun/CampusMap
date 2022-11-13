package com.myproject.campusmap_cleanarchitecture.ui.adapter.bus

import androidx.recyclerview.widget.RecyclerView
import com.myproject.campusmap_cleanarchitecture.databinding.BusStopListItemBinding
import com.myproject.campusmap_cleanarchitecture.domain.model.BusStop

class BusStopViewHolder(
    private val binding: BusStopListItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(busStop: BusStop) {
        binding.busStopData = busStop
    }

}