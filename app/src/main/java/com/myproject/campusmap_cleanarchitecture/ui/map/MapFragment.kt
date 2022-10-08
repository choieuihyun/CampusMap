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
import androidx.work.impl.model.Preference
import com.myproject.campusmap_cleanarchitecture.R
import com.myproject.campusmap_cleanarchitecture.databinding.FragmentMapBinding
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapReverseGeoCoder
import net.daum.mf.map.api.MapView
import timber.log.Timber
import java.util.prefs.Preferences


class MapFragment : Fragment(), MapView.CurrentLocationEventListener, MapReverseGeoCoder.ReverseGeoCodingResultListener {

    private lateinit var binding: FragmentMapBinding
    private lateinit var backPressedCallbacks: OnBackPressedCallback
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var mapView : MapView

    private val ACCESS_FINE_LOCATION = 1000     // Request Code

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

        mapView.setCurrentLocationEventListener(this)
        mapView.currentLocationTrackingMode = MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        drawerLayout = view.findViewById(R.id.drawer_layout)

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
        mapView.currentLocationTrackingMode = MapView.CurrentLocationTrackingMode.TrackingModeOff
    }

    companion object {
        fun newInstance() : MapFragment {
            return MapFragment()
        }
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

}