package com.myproject.campusmap_cleanarchitecture.ui.map

import android.Manifest
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.work.impl.model.Preference
import com.myproject.campusmap_cleanarchitecture.R
import com.myproject.campusmap_cleanarchitecture.databinding.FragmentMapBinding
import com.myproject.campusmap_cleanarchitecture.domain.model.Building
import com.myproject.campusmap_cleanarchitecture.ui.building.buildingmenu.BuildingMenuFragmentDirections
import net.daum.mf.map.api.*
import timber.log.Timber
import java.util.prefs.Preferences


class MapFragment : Fragment(), MapView.CurrentLocationEventListener, MapReverseGeoCoder.ReverseGeoCodingResultListener, MapView.POIItemEventListener {

    private lateinit var binding: FragmentMapBinding
    private lateinit var mapPoint: MapPoint
    private lateinit var marker: MapPOIItem // 그냥 lateinit 해봤음 안해도 될듯 ㅋㅋ;;
    private lateinit var backPressedCallbacks: OnBackPressedCallback
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var mapView : MapView

    // private val args by navArgs<MapFragmentArgs>()
    private val args by navArgs<MapFragmentArgs>()
    private val ACCESS_FINE_LOCATION = 1000     // Request Code

    private var onItemClickListener: ((Building) -> Unit)? = null
    fun setOnItemClickListener(listener: (Building) -> Unit) {
        onItemClickListener = listener
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        backPressedCallbacks = object : OnBackPressedCallback(true) { // DrawerLayout을 닫기위한 쇼
            override fun handleOnBackPressed() { // 근데 이러니까 뒤로가기로 앱이 안닫혀 이럴거 같더라 MapFragment의 onAttach에 했으니.
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

        Timber.tag("sdf").d("이거 됨?")

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


/*        binding.buildingSearchButton.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_mapFragment_to_searchFragment)
        }*/



/*        binding.menuNotice.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_buildingMenuFragment_to_noticeFragment)
        }*/

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        mapView = MapView(context)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_map, container, false)

        binding.map.addView(mapView)



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapView.setPOIItemEventListener(this)
        drawerLayout = view.findViewById(R.id.drawer_layout)

        val building = args.building

            binding.map.apply {
                if (building != null) {
                    createMarker(building)
                }
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



    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.map.removeView(mapView)
        val parent = mapView.parent as ViewGroup?
        parent?.removeView(mapView)
        // binding.mapFragment.mapView 야 이거쓰면 무슨 item 제거도 되네
        //mapView.currentLocationTrackingMode = MapView.CurrentLocationTrackingMode.TrackingModeOff
    }

    private fun createMarker(building: Building) { // 그냥 이거 구조 자체가 찝찝함
        mapPoint = MapPoint.mapPointWithGeoCoord(building.latitude!!, building.longitude!!)
        marker = MapPOIItem()
        marker.itemName = building.name
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


    }

    @Deprecated("Deprecated in Java")
    override fun onCalloutBalloonOfPOIItemTouched(p0: MapView?, p1: MapPOIItem?) {

    }

    override fun onCalloutBalloonOfPOIItemTouched(
        p0: MapView?,
        p1: MapPOIItem?,
        p2: MapPOIItem.CalloutBalloonButtonType?,
    ) {
        val action = MapFragmentDirections.actionMapFragmentToBuildingDetailFragment(args.building!!)
        findNavController().navigate(action)
    }

    override fun onDraggablePOIItemMoved(p0: MapView?, p1: MapPOIItem?, p2: MapPoint?) {

    }

}