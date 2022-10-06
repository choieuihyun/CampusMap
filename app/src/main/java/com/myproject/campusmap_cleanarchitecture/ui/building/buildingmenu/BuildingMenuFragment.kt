package com.campusmap.android.campusmap_with_kakao

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.campusmap.android.campusmap_with_kakao.building.Building
import com.campusmap.android.campusmap_with_kakao.databinding.BuildingMenuFragmentBinding

private const val TAG = "BuildingMenuFragment"

class BuildingMenuFragment: Fragment() {

    private lateinit var binding: BuildingMenuFragmentBinding
    private lateinit var testView: View

    // 여기다가 데이터를 띄워주고 싶어. 그러면 데이터를 액세스하는곳을 알아서 데이터를 가져와야겠지. 그게 바로 Repository고.
    private lateinit var buildingRecyclerView: RecyclerView
    private var adapter: BuildingAdapter? = BuildingAdapter(emptyList())

    // 추가부분
    private val buildingListViewModel: BuildingMenuViewModel by lazy {
        ViewModelProvider(this).get(BuildingMenuViewModel::class.java)
    }


    interface Callbacks {
        fun onMenuButtonSelected(id : Int)
        fun onBuildingSelected(id: Int)
    }

    private var callbacks: Callbacks? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = DataBindingUtil.inflate(inflater, R.layout.building_menu_fragment, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 추가부분
        buildingRecyclerView = view.findViewById(R.id.building_recycler_view) as RecyclerView
        buildingRecyclerView.layoutManager = LinearLayoutManager(context)
        buildingRecyclerView.adapter = adapter

        // LiveData 인스턴스에 옵저버를 등록, 추가부분
//        binding.btn1.setOnClickListener {
//
//        }

        binding.run {

            btn1.setOnClickListener {
                buildingListViewModel.buildingsLiveData.observe(
                    viewLifecycleOwner,
                    { buildings ->
                        buildings.let {
                            Log.i("gggg","Got buildings ${buildings.size}")
                            updateUI(buildings.subList(0,9))
                        }
                    }
                )
            }

            btn2.setOnClickListener {
                buildingListViewModel.buildingsLiveData.observe(
                    viewLifecycleOwner,
                    { buildings ->
                        buildings.let {
                            Log.i("gggg","Got buildings ${buildings.size}")
                            updateUI(buildings.subList(9,15))
                        }
                    }
                )
            }

            btn3.setOnClickListener {
                buildingListViewModel.buildingsLiveData.observe(
                    viewLifecycleOwner,
                    { buildings ->
                        buildings.let {
                            Log.i("gggg","Got buildings ${buildings.size}")
                            updateUI(buildings.subList(15,27))
                        }
                    }
                )
            }

            btn4.setOnClickListener {
                buildingListViewModel.buildingsLiveData.observe(
                    viewLifecycleOwner,
                    { buildings ->
                        buildings.let {
                            Log.i("gggg","Got buildings ${buildings.size}")
                            updateUI(buildings.subList(29,39))
                        }
                    }
                )
            }

            btn5.setOnClickListener {
                buildingListViewModel.buildingsLiveData.observe(
                    viewLifecycleOwner,
                    { buildings ->
                        buildings.let {
                            Log.i("gggg","Got buildings ${buildings.size}")
                            updateUI(buildings.subList(39,43))
                        }
                    }
                )
            }

            btn6.setOnClickListener {
                buildingListViewModel.buildingsLiveData.observe(
                    viewLifecycleOwner,
                    { buildings ->
                        buildings.let {
                            Log.i("gggg","Got buildings ${buildings.size}")
                            updateUI(buildings.subList(27,29))
                        }
                    }
                )
            }

        }



    }

    // 추가부분
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

        override fun onClick(v: View?) {
            callbacks?.onBuildingSelected(buildings.id)
        }
    }

    // 추가부분
    private inner class BuildingAdapter(var buildings: List<Building>) :
        RecyclerView.Adapter<BuildingHolder>() {

        // inflate후 만들 Holder 인스턴스를 return
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuildingHolder {
            val view = layoutInflater.inflate(R.layout.list_item_building, parent, false)
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

    // 추가부분
    private fun onButtonClick(id: Int) {
        callbacks?.onMenuButtonSelected(id)
    }

    companion object {
        fun newInstance() : BuildingMenuFragment {
            return BuildingMenuFragment()
        }
    }


}