package com.myproject.campusmap_cleanarchitecture.presentation.adapter.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.myproject.campusmap_cleanarchitecture.domain.model.Building
import com.myproject.campusmap_cleanarchitecture.presentation.databinding.SearchFragmentBuildingListItemBinding

class SearchBuildingAdapter : ListAdapter<Building, SearchBuildingViewHolder>(buildingDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchBuildingViewHolder {
        return SearchBuildingViewHolder(
            SearchFragmentBuildingListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: SearchBuildingViewHolder, position: Int) {
        val building = currentList[position]
        holder.bind(building)
        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(building)
            }
        }
    }

    private var onItemClickListener : ((Building) -> Unit)? = null

    fun setOnItemClickListener(listener: (Building) -> Unit) {
        onItemClickListener = listener
    }

    companion object {

        private val buildingDiffCallback = object : DiffUtil.ItemCallback<Building>() {
            override fun areItemsTheSame(oldItem: Building, newItem: Building): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

            override fun areContentsTheSame(oldItem: Building, newItem: Building): Boolean {
                return oldItem == newItem
            }

        }
    }

}