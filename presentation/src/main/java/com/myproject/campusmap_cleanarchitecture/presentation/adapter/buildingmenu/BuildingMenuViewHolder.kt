package com.myproject.campusmap_cleanarchitecture.presentation.adapter.buildingmenu

import androidx.recyclerview.widget.RecyclerView
import com.myproject.campusmap_cleanarchitecture.domain.model.Building
import com.myproject.campusmap_cleanarchitecture.presentation.databinding.BuildingListItemBinding

class BuildingMenuViewHolder(
    private val binding: BuildingListItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(building: Building) {

        //binding.buildingName.text = building.name
        binding.building = building

    }

/*    override fun itemClick(id: Int) {
        //Navigation.findNavController(binding.root).navigate(R.id.action_buildingMenuFragment_to_buildingDetailFragment)
    }*/


}