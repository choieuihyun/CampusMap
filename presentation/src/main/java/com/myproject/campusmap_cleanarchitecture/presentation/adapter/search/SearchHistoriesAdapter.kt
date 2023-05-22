package com.myproject.campusmap_cleanarchitecture.presentation.adapter.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.myproject.campusmap_cleanarchitecture.domain.model.BuildingHistory
import com.myproject.campusmap_cleanarchitecture.presentation.databinding.SearchFragmentBuildingHistoryListItemBinding

class SearchHistoriesAdapter : ListAdapter<BuildingHistory, SearchHistoriesViewHolder>(
    buildingHistoriesDiffCallback
) {

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
        holder.setOnButtonClickListener {
            onButtonClickListener?.let {
                it(history)
            }
        }
    }

    private var onItemClickListener : ((BuildingHistory) -> Unit)? = null
    private var onButtonClickListener : ((BuildingHistory) -> Unit)? = null

    fun setOnItemClickListener(listener : (BuildingHistory) -> Unit) {
        onItemClickListener = listener
    }

    fun setOnButtonClickListener(listener : (BuildingHistory) -> Unit) {
        onButtonClickListener = listener
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