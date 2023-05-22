package com.myproject.campusmap_cleanarchitecture.presentation.bus

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.myproject.campusmap_cleanarchitecture.presentation.BaseFragment
import com.myproject.campusmap_cleanarchitecture.presentation.R
import com.myproject.campusmap_cleanarchitecture.presentation.adapter.bus.BusStopAdapter
import com.myproject.campusmap_cleanarchitecture.presentation.databinding.BusStopFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BusStopFragment : BaseFragment<BusStopFragmentBinding>(R.layout.bus_stop_fragment) {

    private lateinit var busStopAdatper: BusStopAdapter

    private val viewModel : BusStopViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        viewModel.getBusStopData()

        updateList()
    }

    private fun updateList() {
        viewModel.busStopDatas.observe(viewLifecycleOwner) {
            busStop -> busStopAdatper.submitList(busStop)
        }
    }

    private fun setupRecyclerView() {
        busStopAdatper = BusStopAdapter()
        binding.busStopRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            adapter = busStopAdatper
        }

        busStopAdatper.setOnItemClickListener {
            busStop ->
            val action = BusStopFragmentDirections.actionBusStopFragmentToMapFragment(building = null, busStop ,buildingHistory = null)
            findNavController().navigate(action)
        }
    }


}