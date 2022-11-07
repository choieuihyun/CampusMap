package com.myproject.campusmap_cleanarchitecture.ui.adapter.buildingmenu

import android.util.Log
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.myproject.campusmap_cleanarchitecture.R
import com.myproject.campusmap_cleanarchitecture.databinding.BuildingFragmentMenuBinding
import com.myproject.campusmap_cleanarchitecture.databinding.BuildingListItemBinding
import com.myproject.campusmap_cleanarchitecture.domain.model.Building
import com.myproject.campusmap_cleanarchitecture.ui.building.buildingmenu.BuildingMenuFragment

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