package com.myproject.campusmap_cleanarchitecture.ui.adapter.buildingfavorite

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.myproject.campusmap_cleanarchitecture.databinding.BuildingFragmentFavoriteListItemBinding
import com.myproject.campusmap_cleanarchitecture.domain.model.BuildingFavorite
import com.myproject.campusmap_cleanarchitecture.ui.building.buildingfavorite.BuildingFavoriteViewModel

class BuildingFavoriteViewHolder(
    private val binding: BuildingFragmentFavoriteListItemBinding,
    private val viewModel: BuildingFavoriteViewModel
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(c: Context, path: String, buildingFavorite: BuildingFavorite) {
        viewModel.getBuildingImages(c, path, binding.buildingFavoriteImageView)
        binding.buildingFavorite = buildingFavorite

        val checkboxState = viewModel.getBuildingDetailCheckboxState(buildingFavorite.id)
        binding.buildingDetailFavorite.isChecked = checkboxState
        binding.buildingDetailFavorite.isSelected = checkboxState

        // name에 직접 때려박을땐 recyclerview 안쓸때나 그렇게 되는거고.

        binding.buildingDetailFavorite.setOnClickListener {

            it.isSelected = !it.isSelected
            viewModel.checkboxState = it.isSelected
            viewModel.setBuildingDetailCheckboxState(buildingFavorite.id, viewModel.checkboxState)
            viewModel.deleteBuildingFavorite(buildingFavorite.id)

        }
    }

}