package com.myproject.campusmap_cleanarchitecture.ui.building.buildingdetail
import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.myproject.campusmap_cleanarchitecture.R
import com.myproject.campusmap_cleanarchitecture.databinding.BuildingFragmentDetailBinding
import com.myproject.campusmap_cleanarchitecture.domain.model.Building
import com.myproject.campusmap_cleanarchitecture.domain.model.BuildingHistory
import com.myproject.campusmap_cleanarchitecture.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BuildingDetailFragment: BaseFragment<BuildingFragmentDetailBinding>(R.layout.building_fragment_detail) {

    private val args by navArgs<BuildingDetailFragmentArgs>()

    // private val sharedPreferences = context?.getSharedPreferences(getString(R.string.spk),Context.MODE_PRIVATE)

    private val viewModel: BuildingDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val building = args.building
        val buildingHistory = args.buildingHistory

        binding.buildingDetailViewModel = viewModel



        if (building != null) {

            updateBuildingUI(building)

            updateBuildingImages(requireActivity(), building.buildingImageUri.toString(), binding.buildingImage)

            checkboxState(building.id)

        } else if (buildingHistory != null) {

            updateBuildingHistoryUI(buildingHistory)

            updateBuildingImages(requireActivity(), buildingHistory.buildingImageUri.toString(), binding.buildingImage)

            checkboxState(buildingHistory.id)

        }

        binding.buildingDetailFavorite.setOnClickListener {

            if (building != null) {
                it.isSelected = it.isSelected != true
                viewModel.checkboxState = it.isSelected
                Log.d("checkboxState2", viewModel.checkboxState.toString())
                viewModel.setBuildingDetailCheckboxState(building.id, viewModel.checkboxState)
            }
        }
    }

    private fun updateBuildingUI(building: Building) {

        viewModel.getBuildingDetailData(building)

        if(building.buildingImageUri?.contains("no_detail") == true) {
            binding.lectureRoomButton.visibility = View.GONE
        }

        binding.lectureRoomButton.setOnClickListener {
            val action = BuildingDetailFragmentDirections.actionBuildingDetailFragmentToLectureRoomMenu(building, buildingHistory = null)
            findNavController().navigate(action)
        }

    }

    private fun updateBuildingHistoryUI(buildingHistory: BuildingHistory) {

        viewModel.getBuildingDetailData(buildingHistory)

        if(buildingHistory.buildingImageUri?.contains("no_detail") == true) {
            binding.lectureRoomButton.visibility = View.GONE
        }

        binding.lectureRoomButton.setOnClickListener {
            val action = BuildingDetailFragmentDirections.actionBuildingDetailFragmentToLectureRoomMenu(building = null, buildingHistory)
            findNavController().navigate(action)
        }

    }

    private fun updateBuildingImages(c: Activity, path: String, v: ImageView) {
        viewModel.getBuildingImages(c, path, v)
    }

    private fun checkboxState(id: Int) {
        val checkboxState = viewModel.getBuildingDetailCheckboxState(id)
        binding.buildingDetailFavorite.isChecked = checkboxState
    }

}