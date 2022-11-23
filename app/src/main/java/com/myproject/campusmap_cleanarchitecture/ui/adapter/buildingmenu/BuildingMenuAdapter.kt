package com.myproject.campusmap_cleanarchitecture.ui.adapter.buildingmenu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.myproject.campusmap_cleanarchitecture.databinding.BuildingListItemBinding
import com.myproject.campusmap_cleanarchitecture.domain.model.Building

class BuildingMenuAdapter : ListAdapter<Building, BuildingMenuViewHolder>(BuildingMenuDiffCallback)  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuildingMenuViewHolder {
        return BuildingMenuViewHolder(
            BuildingListItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        )
    }

    override fun onBindViewHolder(holder: BuildingMenuViewHolder, position: Int) {
        val building = currentList[position]
        holder.bind(building)
        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(building) }
        }
    }

    private var onItemClickListener: ((Building) -> Unit)? = null

    fun setOnItemClickListener(listener: (Building) -> Unit) {
        onItemClickListener = listener
    }


    companion object {

        private val BuildingMenuDiffCallback = object : DiffUtil.ItemCallback<Building>() {
            override fun areItemsTheSame(oldItem: Building, newItem: Building): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

            override fun areContentsTheSame(oldItem: Building, newItem: Building): Boolean {
                return oldItem == newItem
            }

        }
    }


}