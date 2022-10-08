package com.myproject.campusmap_cleanarchitecture.ui.building.buildingmenu

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.campusmap.android.campusmap_with_kakao.BuildingMenuViewModel
import com.myproject.campusmap_cleanarchitecture.R
import com.myproject.campusmap_cleanarchitecture.databinding.BuildingFragmentMenuBinding
import com.myproject.campusmap_cleanarchitecture.domain.model.Building
import com.myproject.campusmap_cleanarchitecture.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "BuildingMenuFragment"

@AndroidEntryPoint
class BuildingMenuFragment: BaseFragment<BuildingFragmentMenuBinding>(R.layout.building_fragment_menu) {

    private lateinit var buildingRecyclerView: RecyclerView
//    private var adapter: BuildingAdapter? = BuildingAdapter(emptyList())

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

        binding.btn1.setOnClickListener {
            // LiveData로 RecyclerView 갱신.
            viewModel.getBuildings.observe()
        }

        binding.btn2.setOnClickListener {

        }

        binding.btn3.setOnClickListener {

        }

        binding.btn4.setOnClickListener {

        }

        binding.btn5.setOnClickListener {

        }

        binding.btn6.setOnClickListener {

        }

/*        아주 가끔 뷰 객체가 생성되지 않는 경우도 있기 때문에 되도록이면 콜백을 받는 onViewCreated에서 View의 초기값을 설정해주거나,
        adapter를 설정하거나, 이벤트를 정의하거나 해주면 된다.*/

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
        // Fragment는 오래 지속되므로 onDestroyView() 메소드에서 binding 클래스 인스턴스에 대한 참조를 정리해줘야한다.
        // 라고해서 일단 했는데 더 찾아보자 어쩔때 하고 어쩔때 안하는지.
        //binding = null
    }

/*    // 추가부분
    private inner class BuildingHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        private lateinit var buildings: Building

        private val titleTextView: TextView = view.findViewById(R.id.building_title)

        init {
            view.setOnClickListener(this)
        }

        fun bind(buildings: Building) {
            this.buildings = buildings
            titleTextView.text = this.buildings.name
        }

    }

    // 추가부분
    private inner class BuildingAdapter(var buildings: List<Building>) :
        RecyclerView.Adapter<BuildingHolder>() {

        // inflate후 만들 Holder 인스턴스를 return
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuildingHolder {
            val view = layoutInflater.inflate(R.layout.building_list_item, parent, false)
            return BuildingHolder(view)
        }

        override fun onBindViewHolder(holder: BuildingHolder, position: Int) {
            val building = buildings[position]
            holder.bind(building)
        }

        override fun getItemCount(): Int {
            Log.d("sdf", buildings.size.toString())
            return buildings.size
        }
    }

    private fun updateUI(buildings: List<Building>) {
        adapter = BuildingAdapter(buildings)
        buildingRecyclerView.adapter = adapter
    }


    companion object {
        fun newInstance() : BuildingMenuFragment {
            return BuildingMenuFragment()
        }
    }*/


}