package com.myproject.campusmap_cleanarchitecture.ui.bus

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.myproject.campusmap_cleanarchitecture.R
import com.myproject.campusmap_cleanarchitecture.databinding.BusPositionFragmentBinding
import com.myproject.campusmap_cleanarchitecture.ui.BaseFragment
import com.myproject.campusmap_cleanarchitecture.ui.adapter.bus.BusPositionAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BusPositionFragment : BaseFragment<BusPositionFragmentBinding>(R.layout.bus_position_fragment) {

    private val viewModel : BusPositionViewModel by viewModels()
    private val args by navArgs<BusPositionFragmentArgs>()

    private lateinit var busPositionAdapter : BusPositionAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        try {
        viewModel.getBusPositionData(args.busStandardId)
        } catch (e: java.lang.NullPointerException){
            Toast.makeText(context,"버스가 없음",Toast.LENGTH_LONG).show()
        }

        updateList()

        binding.refresh.setOnRefreshListener {
            viewModel.getBusPositionData(args.busStandardId)
            binding.refresh.isRefreshing = false
        }

    }

    private fun updateList() {
        viewModel.busPositionDatas.observe(viewLifecycleOwner) {
            busDatas ->
                busPositionAdapter.submitList(busDatas)
        }
    }

    private fun setupRecyclerView() {

        busPositionAdapter = BusPositionAdapter()

        binding.busInfoRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            adapter = busPositionAdapter
        }
    }

}