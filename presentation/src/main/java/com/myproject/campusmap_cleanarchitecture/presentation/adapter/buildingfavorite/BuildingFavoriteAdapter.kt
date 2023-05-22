package com.myproject.campusmap_cleanarchitecture.presentation.adapter.buildingfavorite

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.myproject.campusmap_cleanarchitecture.domain.model.BuildingFavorite
import com.myproject.campusmap_cleanarchitecture.presentation.databinding.BuildingFragmentFavoriteListItemBinding
import com.myproject.campusmap_cleanarchitecture.presentation.building.buildingfavorite.BuildingFavoriteViewModel

class BuildingFavoriteAdapter(private val context: Context,
                              private val viewModel: BuildingFavoriteViewModel
) :ListAdapter<BuildingFavorite, BuildingFavoriteViewHolder>(
    buildingFavoriteDiffCallback
){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuildingFavoriteViewHolder {
        return BuildingFavoriteViewHolder(BuildingFragmentFavoriteListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false), viewModel)
    }

    override fun onBindViewHolder(holder: BuildingFavoriteViewHolder, position: Int) {
        val buildingFavorite = currentList[position]
        holder.bind(context, buildingFavorite.buildingImageUri.toString(), buildingFavorite) // 저거 context 고쳐

        holder.setOnButtonClickListener {
            onButtonClickListener?.let {
                it(buildingFavorite)
            }
        }
    }

    private var onButtonClickListener : ((BuildingFavorite) -> Unit)? = null

    fun setOnButtonClickListener(listener : (BuildingFavorite) -> Unit) {
        onButtonClickListener = listener
    }

    companion object {

        private val buildingFavoriteDiffCallback = object : DiffUtil.ItemCallback<BuildingFavorite>() {
            override fun areItemsTheSame(oldItem: BuildingFavorite, newItem: BuildingFavorite): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

            override fun areContentsTheSame(oldItem: BuildingFavorite, newItem: BuildingFavorite): Boolean {
                return oldItem.id == newItem.id // equals 에러가 왜떴지?
            }

        }
    }


}