package com.myproject.campusmap_cleanarchitecture.presentation.building.buildingdetail
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.myproject.campusmap_cleanarchitecture.domain.model.Building
import com.myproject.campusmap_cleanarchitecture.domain.model.BuildingFavorite
import com.myproject.campusmap_cleanarchitecture.domain.model.BuildingHistory
import com.myproject.campusmap_cleanarchitecture.presentation.BaseFragment
import com.myproject.campusmap_cleanarchitecture.presentation.R
import com.myproject.campusmap_cleanarchitecture.presentation.databinding.BuildingFragmentDetailBinding
import com.myproject.campusmap_cleanarchitecture.ui.building.buildingdetail.BuildingDetailFragmentArgs
import com.myproject.campusmap_cleanarchitecture.ui.building.buildingdetail.BuildingDetailFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BuildingDetailFragment: BaseFragment<BuildingFragmentDetailBinding>(R.layout.building_fragment_detail) {

    private val args by navArgs<BuildingDetailFragmentArgs>()

    private val viewModel: BuildingDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val building = args.building
        val buildingHistory = args.buildingHistory
        val buildingFavorite = args.buildingFavorite

        binding.buildingDetailViewModel = viewModel

        if (building != null) {

            updateBuildingUI(building)

            updateBuildingImages(requireActivity(), building.buildingImageUri.toString(), binding.buildingImage)

            checkboxState(building.id)

        } else if (buildingHistory != null) {

            updateBuildingHistoryUI(buildingHistory)

            updateBuildingImages(requireActivity(), buildingHistory.buildingImageUri.toString(), binding.buildingImage)

            checkboxState(buildingHistory.id)

        } else if (buildingFavorite != null) {

            updateBuildingFavoriteUI(buildingFavorite)

            updateBuildingImages(requireActivity(), buildingFavorite.buildingImageUri.toString(), binding.buildingImage)

            checkboxState(buildingFavorite.id)

        }

        binding.buildingDetailFavorite.setOnClickListener {

            if (building != null) {

                it.isSelected = !it.isSelected
                viewModel.checkboxState = it.isSelected
                viewModel.setBuildingDetailCheckboxState(building.id, viewModel.checkboxState)

                if(viewModel.checkboxState)
                    viewModel.addBuildingDetailFavorite(building)
                else
                    viewModel.deleteBuildingDetailFavorite(building.id)

            } else if (buildingHistory != null) {

                it.isSelected = !it.isSelected
                viewModel.checkboxState = it.isSelected
                viewModel.setBuildingDetailCheckboxState(buildingHistory.id, viewModel.checkboxState)

                if(viewModel.checkboxState)
                    viewModel.addBuildingDetailFavorite(buildingHistory)
                else
                    viewModel.deleteBuildingDetailFavorite(buildingHistory.id)

            } else if (buildingFavorite != null) {

                it.isSelected = !it.isSelected
                viewModel.checkboxState = it.isSelected
                viewModel.setBuildingDetailCheckboxState(buildingFavorite.id, viewModel.checkboxState)

                if(viewModel.checkboxState)
                    viewModel.addBuildingDetailFavorite(buildingFavorite)
                else
                    viewModel.deleteBuildingDetailFavorite(buildingFavorite.id)

            }
        }

        binding.shareButton.setOnClickListener {

            shareSheet()

        }

    }

    private fun updateBuildingUI(building: Building) {

        viewModel.getBuildingDetailData(building)

        if(building.buildingImageUri?.contains("no_detail") == true) {
            binding.lectureRoomButton.visibility = View.GONE
        }

        binding.lectureRoomButton.setOnClickListener {
            val action = BuildingDetailFragmentDirections.actionBuildingDetailFragmentToLectureRoomMenu(building, buildingHistory = null, buildingFavorite = null)
            findNavController().navigate(action)
        }

    }

    private fun updateBuildingHistoryUI(buildingHistory: BuildingHistory) {

        viewModel.getBuildingDetailData(buildingHistory)

        if(buildingHistory.buildingImageUri?.contains("no_detail") == true) {
            binding.lectureRoomButton.visibility = View.GONE
        }

        binding.lectureRoomButton.setOnClickListener {
            val action = BuildingDetailFragmentDirections.actionBuildingDetailFragmentToLectureRoomMenu(building = null, buildingHistory, buildingFavorite = null)
            findNavController().navigate(action)
        }

    }

    private fun updateBuildingFavoriteUI(buildingFavorite: BuildingFavorite) {

        viewModel.getBuildingDetailData(buildingFavorite)

        if(buildingFavorite.buildingImageUri?.contains("no_detail") == true) {
            binding.lectureRoomButton.visibility = View.GONE
        }

        binding.lectureRoomButton.setOnClickListener {
            val action = BuildingDetailFragmentDirections.actionBuildingDetailFragmentToLectureRoomMenu(building = null, buildingHistory = null, buildingFavorite)
            findNavController().navigate(action)
        }

    }

    private fun shareSheet() {

        val sendIntent: Intent = Intent().apply {

            action = Intent.ACTION_SEND

            putExtra(Intent.EXTRA_TEXT, "Hello World")

            type = "text/plain"

        }

        val shareIntent = Intent.createChooser(sendIntent, null)

        startActivity(shareIntent)

    }

    private fun updateBuildingImages(c: Context, path: String, v: ImageView) {
        viewModel.getBuildingImages(c, path, v)
    }

    private fun checkboxState(id: Int) {
        val checkboxState = viewModel.getBuildingDetailCheckboxState(id)
        binding.buildingDetailFavorite.isChecked = checkboxState
        binding.buildingDetailFavorite.isSelected = checkboxState
    }

}