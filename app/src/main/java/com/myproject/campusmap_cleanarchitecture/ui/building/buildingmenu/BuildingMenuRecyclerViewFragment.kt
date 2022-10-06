package com.campusmap.android.campusmap_with_kakao

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.campusmap.android.campusmap_with_kakao.building.Building

class BuildingMenuRecyclerViewFragment : Fragment() {

/*
    나는 xml이랑 이 프래그먼트 클래스 따로 만들어서 include로 building_menu_fragment에다가 넣어줄려했는데 사실 그럴 필요가 없었지. 너무 많이갔어.
    // 여기다가 데이터를 띄워주고 싶어. 그러면 데이터를 액세스하는곳을 알아서 데이터를 가져와야겠지. 그게 바로 Repository고.
    private lateinit var buildingRecyclerView: RecyclerView
    private var adapter: BuildingAdapter? = BuildingAdapter(emptyList())

*/
/*    interface Callbacks {
        fun onMenuButtonSelected(id : Int)
    }*//*


    private val buildingListViewModel: BuildingMenuViewModel by lazy {
        ViewModelProvider(this).get(BuildingMenuViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.building_menu_fragment_detail_recyclerview, container, false)

        buildingRecyclerView = view.findViewById(R.id.building_recycler_view) as RecyclerView
        buildingRecyclerView.layoutManager = LinearLayoutManager(context)
        buildingRecyclerView.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // LiveData 인스턴스에 옵저버를 등록
        buildingListViewModel.buildingsLiveData.observe(
            viewLifecycleOwner,
            { buildings ->
                buildings.let {
                    Log.i("gggg","Got buildings ${buildings.size}")
                    updateUI(buildings)
                }
            }
        )


    }

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

        }


    }

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

    companion object {
        fun newInstance(): BuildingMenuRecyclerViewFragment {
            return BuildingMenuRecyclerViewFragment()
        }
    }
*/



}

