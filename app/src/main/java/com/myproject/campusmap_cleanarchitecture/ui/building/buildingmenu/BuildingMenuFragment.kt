package com.myproject.campusmap_cleanarchitecture.ui.building.buildingmenu

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myproject.campusmap_cleanarchitecture.R
import com.myproject.campusmap_cleanarchitecture.databinding.BuildingFragmentMenuBinding
import com.myproject.campusmap_cleanarchitecture.ui.BaseFragment
import com.myproject.campusmap_cleanarchitecture.ui.adapter.BuildingMenuAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BuildingMenuFragment: BaseFragment<BuildingFragmentMenuBinding>(R.layout.building_fragment_menu) {

    private lateinit var buildingMenuAdapter: BuildingMenuAdapter

    private val viewModel: BuildingMenuViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //화면에 보이는 뷰들의 일반적인 상태를 설정하는 작업을 한다.


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /* 아주 가끔 뷰 객체가 생성되지 않는 경우도 있기 때문에 되도록이면 콜백을 받는 onViewCreated에서 View의 초기값을 설정해주거나,
        adapter를 설정하거나, 이벤트를 정의하거나 해주면 된다.*/

        setupRecyclerView()

        binding.btn1.setOnClickListener {
            // LiveData로 RecyclerView 갱신.
            updateList(0,9)
        }

        binding.btn2.setOnClickListener {
            updateList(9,15)
        }

        binding.btn3.setOnClickListener {
            updateList(15,27)
        }

        binding.btn4.setOnClickListener {
            updateList(29,39)
        }

        binding.btn5.setOnClickListener {
            updateList(39,43)
        }

        binding.btn6.setOnClickListener {
            updateList(27,29)
        }



        // 추가부분
//        buildingRecyclerView = view.findViewById(R.id.building_recycler_view) as RecyclerView
//        buildingRecyclerView.layoutManager = LinearLayoutManager(context)
//        buildingRecyclerView.adapter = adapter

        // LiveData 인스턴스에 옵저버를 등록, 추가부분
//        binding.btn1.setOnClickListener {
//
//        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    private fun updateList(fromIdx: Int, toIdx: Int) {
        viewModel.getBuildings.observe(viewLifecycleOwner) {
                buildings -> buildingMenuAdapter.submitList(buildings.subList(fromIdx,toIdx))
            Log.d("building", buildings.toString())
        }
    }

    private fun setupRecyclerView() {
        //buildingRecyclerView = binding.buildingRecyclerView
        buildingMenuAdapter = BuildingMenuAdapter()
        binding.buildingRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            adapter = buildingMenuAdapter

/*            buildingMenuAdapter.setOnItemClickListener {
                // 이렇게 하기보다는 그냥 ViewHolder에서 clicklistener 구현해서 하는게 맞아보인다. 여기는 building.id를 구할수가 없음.
            }*/
        }

        buildingMenuAdapter.setOnItemClickListener { // args가 필요할땐 이렇게? 공부해야할듯.
            building ->
            val action = BuildingMenuFragmentDirections.actionBuildingMenuFragmentToMapFragment(building)
            findNavController().navigate(action)
        }
    }

}