package com.myproject.campusmap_cleanarchitecture.presentation.adapter.bus

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.myproject.campusmap_cleanarchitecture.domain.model.BusStop
import com.myproject.campusmap_cleanarchitecture.presentation.databinding.BusStopListItemBinding

class BusStopAdapter : ListAdapter<BusStop, BusStopViewHolder>(busStopDiffCallback){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusStopViewHolder {
        return BusStopViewHolder(
            BusStopListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: BusStopViewHolder, position: Int) {
        val busStop = currentList[position]
        holder.bind(busStop)
        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(busStop)
            }
        }
    }

    private var onItemClickListener : ((BusStop) -> Unit)? = null

    fun setOnItemClickListener(listener: (BusStop) -> Unit) {
        onItemClickListener = listener
    }

    companion object {

        private val busStopDiffCallback = object : DiffUtil.ItemCallback<BusStop>() {
            override fun areItemsTheSame(oldItem: BusStop, newItem: BusStop): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

            override fun areContentsTheSame(oldItem: BusStop, newItem: BusStop): Boolean {
                return oldItem == newItem
            }

        }
    }


}