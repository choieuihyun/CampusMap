package com.myproject.campusmap_cleanarchitecture.ui.building.buildingdetail
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.myproject.campusmap_cleanarchitecture.R
import com.myproject.campusmap_cleanarchitecture.databinding.BuildingFragmentDetailBinding
import com.myproject.campusmap_cleanarchitecture.ui.BaseFragment
import com.myproject.campusmap_cleanarchitecture.ui.map.MapFragmentArgs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BuildingDetailFragment: BaseFragment<BuildingFragmentDetailBinding>(R.layout.building_fragment_detail) {

    private val args by navArgs<BuildingDetailFragmentArgs>()

    private val viewModel: BuildingDetailViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val building = args.building

        binding.building = building

        if(building.buildingImageUri?.contains("no_detail") == true) {
            binding.lectureRoomButton.visibility = View.GONE
        }

        updateUI()

        binding.lectureRoomButton.setOnClickListener {
                val action = BuildingDetailFragmentDirections.actionBuildingDetailFragmentToLectureRoomMenu(args.building)
                findNavController().navigate(action)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    private fun updateUI() {
        binding.buildingName.text = binding.building?.name
        viewModel.getBuildingImages(requireActivity(), binding.building?.buildingImageUri!!,binding.buildingImage)
    }


}