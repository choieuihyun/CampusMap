package com.myproject.campusmap_cleanarchitecture.ui.building.buildingdetail
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.myproject.campusmap_cleanarchitecture.R
import com.myproject.campusmap_cleanarchitecture.databinding.BuildingFragmentDetailBinding
import com.myproject.campusmap_cleanarchitecture.domain.model.Building
import com.myproject.campusmap_cleanarchitecture.ui.BaseFragment
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

//    이렇게 돌리면 에러난다. buildingHistory를 이 view에 binding을 못해서 사진이랑 이름이 안뜸.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val building = args.building
        val buildingHistory = args.buildingHistory

        //binding.building = building

        binding.buildingDetailViewModel = viewModel

        if(building != null) {
            updateBuildingUI(building)
        }

        if(building?.buildingImageUri?.contains("no_detail") == true && buildingHistory?.buildingImageUri?.contains("no_detail") == true) {
            binding.lectureRoomButton.visibility = View.GONE
        }

//        updateUI()
//
//        binding.lectureRoomButton.setOnClickListener {
//                val action = BuildingDetailFragmentDirections.actionBuildingDetailFragmentToLectureRoomMenu(building!!)
//                findNavController().navigate(action)
//        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    private fun updateBuildingUI(building: Building) {
        viewModel.getBuildingDetailData(building)

//        binding.buildingName.text = binding.building?.name
//        viewModel.getBuildingImages(requireActivity(), binding.building?.buildingImageUri.toString(),binding.buildingImage)
    }


}