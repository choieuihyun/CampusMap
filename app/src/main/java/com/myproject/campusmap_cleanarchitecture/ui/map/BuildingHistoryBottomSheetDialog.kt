package com.myproject.campusmap_cleanarchitecture.ui.map

import android.content.Context
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.myproject.campusmap_cleanarchitecture.databinding.BuildinghistoryBottomsheetdialogBinding
import com.myproject.campusmap_cleanarchitecture.domain.model.BuildingHistory
import com.myproject.campusmap_cleanarchitecture.ui.search.SearchFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BuildingHistoryBottomSheetDialog() : BottomSheetDialogFragment() {

    private lateinit var binding : BuildinghistoryBottomsheetdialogBinding
    private val args by navArgs<BuildingHistoryBottomSheetDialogArgs>()

    private val viewModel: SearchFragmentViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BuildinghistoryBottomsheetdialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buildingHistory = args.buildingHistory

        binding.buildingHistory = buildingHistory

        viewModel.getBuildingImages(requireContext(), buildingHistory.buildingImageUri.toString(), binding.buildingImage)

        binding.lectureRoomButton.setOnClickListener {
            val action = BuildingHistoryBottomSheetDialogDirections.actionBuildingHistoryBottomSheetDialogToBuildingDetailFragment(building = null, buildingHistory)
            findNavController().navigate(action)
        }
    }

    override fun onResume() {
        super.onResume()
        context?.dialogFragmentResize(this@BuildingHistoryBottomSheetDialog, 0.9f, 0.9f)

    }

    private fun Context.dialogFragmentResize(dialogFragment: BottomSheetDialogFragment, width: Float, height: Float) {
        val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager

        if (Build.VERSION.SDK_INT < 30) {

            val display = windowManager.defaultDisplay
            val size = Point()

            display.getSize(size)

            val window = dialogFragment.dialog?.window

            val x = (size.x * width).toInt()
            val y = (size.y * height).toInt()
            window?.setLayout(x, y)

        } else {

            val rect = windowManager.currentWindowMetrics.bounds

            val window = dialogFragment.dialog?.window

            val x = (rect.width() * width).toInt()
            val y = (rect.height() * height).toInt()

            window?.setLayout(x, y)
        }
    }

}