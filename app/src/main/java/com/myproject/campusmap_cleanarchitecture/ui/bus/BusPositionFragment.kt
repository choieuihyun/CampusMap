package com.myproject.campusmap_cleanarchitecture.ui.bus

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.myproject.campusmap_cleanarchitecture.R
import com.myproject.campusmap_cleanarchitecture.databinding.BusPositionFragmentBinding
import com.myproject.campusmap_cleanarchitecture.databinding.LoadingProgressBinding
import com.myproject.campusmap_cleanarchitecture.ui.BaseFragment
import com.myproject.campusmap_cleanarchitecture.ui.LoadingProgress
import com.myproject.campusmap_cleanarchitecture.ui.adapter.bus.BusPositionAdapter
import com.myproject.campusmap_cleanarchitecture.ui.util.EventObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BusPositionFragment : BaseFragment<BusPositionFragmentBinding>(R.layout.bus_position_fragment) {

    private val viewModel : BusPositionViewModel by viewModels()
    private val args by navArgs<BusPositionFragmentArgs>()
    private lateinit var dialog: Dialog

    private lateinit var busPositionAdapter : BusPositionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dialog = LoadingProgress(requireActivity())
        dialog.show()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        setupUI()

        setupRefresh()

    }

    private fun setupUI() {

        viewModel.getBusPositionData(args.busStandardId)

        viewModel.isError.observe(viewLifecycleOwner) {
            if(it) {
                binding.busInfoRecyclerView.visibility = View.GONE
                binding.busPositionErrorRefresh.root.visibility = View.VISIBLE
            }
        }

        viewModel.busPositionDatas.observe(viewLifecycleOwner) {
            if(it != null) {
                busPositionAdapter.submitList(it)
            } else {
                binding.busInfoRecyclerView.visibility = View.GONE
                binding.busPositionErrorRefresh.root.visibility = View.VISIBLE
            }
        }

        viewModel.showToast.observe(viewLifecycleOwner, EventObserver {
            msg -> Toast.makeText(requireActivity(), msg, Toast.LENGTH_LONG).show()
        })

        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) dialog.show()
            else dialog.dismiss()
        }


    }

    private fun setupRefresh() {

        binding.refresh.setOnRefreshListener {
            viewModel.getBusPositionData(args.busStandardId)
            binding.refresh.isRefreshing = false
        }

        binding.busPositionErrorRefresh.refreshButton.setOnClickListener {
            viewModel.getBusPositionData(args.busStandardId)
            binding.refresh.isRefreshing = false
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