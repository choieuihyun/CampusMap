package com.myproject.campusmap_cleanarchitecture.ui.building.buildingmenu

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.myproject.campusmap_cleanarchitecture.R
import com.myproject.campusmap_cleanarchitecture.databinding.BuildingFragmentMenuBinding
import com.myproject.campusmap_cleanarchitecture.domain.model.Building
import com.myproject.campusmap_cleanarchitecture.domain.model.BusStop
import com.myproject.campusmap_cleanarchitecture.ui.BaseFragment
import com.myproject.campusmap_cleanarchitecture.ui.adapter.buildingmenu.BuildingMenuAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BuildingMenuFragment: BaseFragment<BuildingFragmentMenuBinding>(R.layout.building_fragment_menu) {

    private lateinit var buildingMenuAdapter: BuildingMenuAdapter
    private lateinit var buttonArgs : ArrayList<AppCompatButton>

    private val viewModel: BuildingMenuViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /* 아주 가끔 뷰 객체가 생성되지 않는 경우도 있기 때문에 되도록이면 콜백을 받는 onViewCreated에서 View의 초기값을 설정해주거나,
        adapter를 설정하거나, 이벤트를 정의하거나 해주면 된다.*/

        setupRecyclerView()

        binding.btn1.setOnClickListener {
            changeSelected(0)
            it.isSelected = true
            updateList(1)
        }

        binding.btn2.setOnClickListener {
            changeSelected(1)
            it.isSelected = true
            updateList(2)
        }

        binding.btn3.setOnClickListener {
            changeSelected(2)
            it.isSelected = true
            updateList(3)
        }

        binding.btn4.setOnClickListener {
            changeSelected(3)
            it.isSelected = true
            updateList(4)
        }

        binding.btn5.setOnClickListener {
            changeSelected(4)
            it.isSelected = true
            updateList(5)
        }

        binding.btn6.setOnClickListener {
            changeSelected(5)
            it.isSelected = true
            updateList(6)
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    private fun changeSelected(index: Int) {

        buttonArgs = ArrayList()
        buttonArgs.add(binding.btn1)
        buttonArgs.add(binding.btn2)
        buttonArgs.add(binding.btn3)
        buttonArgs.add(binding.btn4)
        buttonArgs.add(binding.btn5)
        buttonArgs.add(binding.btn6)

        buttonArgs.removeAt(index)

        for(i in 0 until buttonArgs.size) {
            buttonArgs[i].isSelected = false
        }
    }

    private fun updateList(category: Int) {
        viewModel.getBuildings.observe(viewLifecycleOwner) {
                buildings -> buildingMenuAdapter.submitList(buildings.filter { it.category == category.toString() })
        }
    }

    private fun setupRecyclerView() {

        buildingMenuAdapter = BuildingMenuAdapter()
        binding.buildingRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            adapter = buildingMenuAdapter

        }

        buildingMenuAdapter.setOnItemClickListener { // args가 필요할땐 이렇게? 공부해야할듯.
            building ->
            val busStop = BusStop("","","","","","")
            val action = BuildingMenuFragmentDirections.actionBuildingMenuFragmentToMapFragment(
                building,busStop)
            findNavController().navigate(action)
        }
    }

}
