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
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.myproject.campusmap_cleanarchitecture.databinding.MapFragmentBottomsheetdialogBinding
import com.myproject.campusmap_cleanarchitecture.ui.building.buildingdetail.BuildingDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BottomSheetDialog() : BottomSheetDialogFragment() {

    private lateinit var binding: MapFragmentBottomsheetdialogBinding
    private val args by navArgs<BottomSheetDialogArgs>()

    private val viewModel: BuildingDetailViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = MapFragmentBottomsheetdialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.building = args.building
        viewModel.getBuildingImages(requireActivity(), binding.building?.buildingImageUri!!,binding.buildingImage)

        binding.lectureRoomButton.setOnClickListener {
            val action = BottomSheetDialogDirections.actionBottomSheetDialogToBuildingDetailFragment(args.building)
            findNavController().navigate(action)
        }

    }

    override fun onResume() {
        super.onResume()
        context?.dialogFragmentResize(this@BottomSheetDialog, 0.9f, 0.9f)

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