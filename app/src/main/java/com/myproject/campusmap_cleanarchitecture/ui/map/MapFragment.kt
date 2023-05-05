package com.myproject.campusmap_cleanarchitecture.ui.map

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.myproject.campusmap_cleanarchitecture.R
import com.myproject.campusmap_cleanarchitecture.databinding.MapFragmentBinding
import com.myproject.campusmap_cleanarchitecture.domain.model.Building
import com.myproject.campusmap_cleanarchitecture.domain.model.BuildingHistory
import com.myproject.campusmap_cleanarchitecture.domain.model.BusStop
import com.myproject.campusmap_cleanarchitecture.ui.main.MainActivity
import net.daum.mf.map.api.*


class MapFragment : Fragment(), MapView.CurrentLocationEventListener, MapReverseGeoCoder.ReverseGeoCodingResultListener, MapView.POIItemEventListener {

    private lateinit var binding: MapFragmentBinding
    private lateinit var mapPoint: MapPoint
    private lateinit var marker: MapPOIItem
    private lateinit var backPressedCallbacks: OnBackPressedCallback
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var mapView : MapView
    private lateinit var mainActivity: MainActivity

    private val args by navArgs<MapFragmentArgs>()
    private val ACCESS_FINE_LOCATION = 1000

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainActivity = context as MainActivity

        backPressedCallbacks = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if(drawerLayout.isDrawerOpen(Gravity.RIGHT)){
                    drawerLayout.closeDrawers()
                }
                backPressedCallbacks.isEnabled = false
            }

        }
        requireActivity().onBackPressedDispatcher.addCallback(this, backPressedCallbacks)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    // Precise location access granted.
                }
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                    // Only approximate location access granted.
                }
                else -> {
                    // No location access granted.
                }
            }

        }

        locationPermissionRequest.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION))

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        mapView = MapView(context)

        binding = DataBindingUtil.inflate(inflater, R.layout.map_fragment, container, false)

        binding.map.addView(mapView)

        return binding.root
    }

    @SuppressLint("SuspiciousIndentation") // 찾아보자.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapView.setPOIItemEventListener(this)
        drawerLayout = view.findViewById(R.id.drawer_layout)

        mainActivity.getBinding.bottomNavigationView.isVisible = false // Main NavHost의 BottomNavi 지우기.

        val building = args.building
        val busStop = args.busStop
        val buildingHistory = args.buildingHistory

        if(building != null) {

            createBuildingMarker(building)

        } else if (busStop != null) {

            createBusStopMarker(busStop)

        } else if (buildingHistory != null) {

            createBuildingHistoryMarker(buildingHistory)

        }

        binding.locationButton.setOnClickListener {
            mapView.setCurrentLocationEventListener(this)
            mapView.currentLocationTrackingMode = MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading
        }

        binding.menuButton.setOnClickListener {
            drawerLayout.openDrawer(Gravity.RIGHT)
        }

        binding.menuBuilding.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_mapFragment_to_buildingMenuFragment)
        }

        binding.menuNotice.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_mapFragment_to_noticeFragment)
        }

        binding.menuBus.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_mapFragment_to_busStopFragment)
        }

        binding.buildingSearchButton.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_mapFragment_to_searchFragment)
        }

        binding.menuFavorite.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_mapFragment_to_buildingFavoriteFragment)
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.map.removeView(mapView)
        val parent = mapView.parent as ViewGroup?
        parent?.removeView(mapView)
    }

    private fun createBuildingMarker(building: Building) { // 그냥 이거 구조 자체가 찝찝함
        mapPoint = MapPoint.mapPointWithGeoCoord(building.latitude!!, building.longitude!!)
        marker = MapPOIItem()
        marker.itemName = building.name
        marker.mapPoint = mapPoint
        mapView.moveCamera(CameraUpdateFactory.newMapPointAndDiameter(mapPoint, 350f))
        mapView.addPOIItem(marker)
        mapView.currentLocationTrackingMode = MapView.CurrentLocationTrackingMode.TrackingModeOff // 이거 뭔가 찝찝함
    }

    private fun createBusStopMarker(busStop: BusStop) {
        mapPoint = MapPoint.mapPointWithGeoCoord(busStop.stopY.toDouble(), busStop.stopX.toDouble())
        marker = MapPOIItem()
        marker.itemName = busStop.stopKname
        marker.mapPoint = mapPoint
        mapView.moveCamera(CameraUpdateFactory.newMapPointAndDiameter(mapPoint, 350f))
        mapView.addPOIItem(marker)
        mapView.currentLocationTrackingMode = MapView.CurrentLocationTrackingMode.TrackingModeOff // 이거 뭔가 찝찝함
    }

    private fun createBuildingHistoryMarker(buildingHistory: BuildingHistory) {
        mapPoint = MapPoint.mapPointWithGeoCoord(buildingHistory.latitude!!, buildingHistory.longitude!!)
        marker = MapPOIItem()
        marker.itemName = buildingHistory.name
        marker.mapPoint = mapPoint
        mapView.moveCamera(CameraUpdateFactory.newMapPointAndDiameter(mapPoint, 350f))
        mapView.addPOIItem(marker)
        mapView.currentLocationTrackingMode = MapView.CurrentLocationTrackingMode.TrackingModeOff // 이거 뭔가 찝찝함
    }

    override fun onCurrentLocationUpdate(p0: MapView?, p1: MapPoint?, p2: Float) {

    }

    override fun onCurrentLocationDeviceHeadingUpdate(p0: MapView?, p1: Float) {

    }

    override fun onCurrentLocationUpdateFailed(p0: MapView?) {

    }

    override fun onCurrentLocationUpdateCancelled(p0: MapView?) {

    }

    override fun onReverseGeoCoderFoundAddress(p0: MapReverseGeoCoder?, p1: String?) {

    }

    override fun onReverseGeoCoderFailedToFindAddress(p0: MapReverseGeoCoder?) {

    }

    override fun onPOIItemSelected(p0: MapView?, p1: MapPOIItem?) {

        if (args.building != null) {
            val action = MapFragmentDirections.actionMapFragmentToBottomSheetDialog(args.building!!)
            findNavController().navigate(action)
        } else if (args.busStop != null) {
            val action2 = MapFragmentDirections.actionMapFragmentToBusStopBottomSheetDialog(args.busStop!!)
            findNavController().navigate(action2)
        } else if (args.buildingHistory != null) {
            val action3 = MapFragmentDirections.actionMapFragmentToBuildingHistoryBottomSheetDialog(args.buildingHistory!!)
            findNavController().navigate(action3)
        }

    }

    @Deprecated("Deprecated in Java")
    override fun onCalloutBalloonOfPOIItemTouched(p0: MapView?, p1: MapPOIItem?) {

    }

    override fun onCalloutBalloonOfPOIItemTouched(
        p0: MapView?,
        p1: MapPOIItem?,
        p2: MapPOIItem.CalloutBalloonButtonType?,
    ) {
/*
        val action = MapFragmentDirections.actionMapFragmentToBuildingDetailFragment(args.building!!)
        findNavController().navigate(action)

        val action2 = MapFragmentDirections.actionMapFragmentToBuildingDetailFragment(building = null, args.buildingHistory!!)
        findNavController().navigate(action2)*/
    }

    override fun onDraggablePOIItemMoved(p0: MapView?, p1: MapPOIItem?, p2: MapPoint?) {

    }

}