package com.myproject.campusmap_cleanarchitecture.presentation.adapter.bus

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.myproject.campusmap_cleanarchitecture.domain.model.BusPosition
import com.myproject.campusmap_cleanarchitecture.presentation.databinding.BusPositionListItemBinding

class BusPositionAdapter : ListAdapter<BusPosition, BusPositionViewHolder>(busPositionDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusPositionViewHolder {
        return BusPositionViewHolder(
            BusPositionListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: BusPositionViewHolder, position: Int) {
        val busPosition = currentList[position]
        holder.bind(busPosition)
    }

    companion object {

        private val busPositionDiffCallback = object : DiffUtil.ItemCallback<BusPosition>() {
            override fun areItemsTheSame(oldItem: BusPosition, newItem: BusPosition): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

            override fun areContentsTheSame(oldItem: BusPosition, newItem: BusPosition): Boolean {
                return oldItem == newItem
            }

        }
    }


}