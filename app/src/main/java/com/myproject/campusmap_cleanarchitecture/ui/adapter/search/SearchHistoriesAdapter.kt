package com.myproject.campusmap_cleanarchitecture.ui.adapter.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.myproject.campusmap_cleanarchitecture.databinding.SearchFragmentBuildingHistoryListItemBinding
import com.myproject.campusmap_cleanarchitecture.domain.model.BuildingHistory

class SearchHistoriesAdapter : ListAdapter<BuildingHistory, SearchHistoriesViewHolder>(
    buildingHistoriesDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHistoriesViewHolder {
        return SearchHistoriesViewHolder(SearchFragmentBuildingHistoryListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: SearchHistoriesViewHolder, position: Int) {
        val history = currentList[position]
        holder.bind(history)
        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(history)
            }
        }
    }

    private var onItemClickListener : ((BuildingHistory) -> Unit)? = null

    fun setOnItemClickListener(listener : (BuildingHistory) -> Unit) {
        onItemClickListener = listener
    }

    companion object {
        private val buildingHistoriesDiffCallback = object : DiffUtil.ItemCallback<BuildingHistory>() {
            override fun areItemsTheSame(oldItem: BuildingHistory, newItem: BuildingHistory, ): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

            override fun areContentsTheSame(oldItem: BuildingHistory, newItem: BuildingHistory, ): Boolean {
                return oldItem == newItem
            }

        }
    }



}