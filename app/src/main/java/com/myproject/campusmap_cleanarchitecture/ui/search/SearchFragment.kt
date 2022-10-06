package com.campusmap.android.campusmap_with_kakao

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.campusmap.android.campusmap_with_kakao.building.Building
import com.campusmap.android.campusmap_with_kakao.databinding.SearchFragmentBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private lateinit var binding: SearchFragmentBinding
    private lateinit var buildingSearchArray: ArrayList<Building>
    private lateinit var buildingArray: ArrayList<Building>
    private lateinit var buildingSearchRecyclerView: RecyclerView
    private var adapter: BuildingSearchAdapter? = BuildingSearchAdapter(ArrayList())

    interface Callbacks {
        fun onSearchBuilding(id: Int)
    }

    private var callbacks: Callbacks? = null

    private val searchFragmentViewModel : SearchFragmentViewModel by lazy {
        ViewModelProvider(this).get(SearchFragmentViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as MainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        buildingSearchArray = ArrayList(emptyList())
        buildingArray = ArrayList(emptyList())

//        lifecycleScope.launch {
//
//
//
//        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.search_fragment, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buildingSearchRecyclerView = view.findViewById(R.id.buildingSearch_RecyclerView) as RecyclerView
        buildingSearchRecyclerView.layoutManager = LinearLayoutManager(context)
        buildingSearchRecyclerView.adapter = adapter

        lifecycleScope.launch { // lifecycleScope를 이렇게 쓰는게 맞냐가 이제 관건이지
                                // 지금 나의 coroutine은 그냥 갈겨쓰는 느낌.

                searchFragmentViewModel.buildingsData.observe(
                    viewLifecycleOwner,
                    { buildings ->
                        buildings.let {
                            buildingArray = buildings as ArrayList<Building>
                        }
                    }
                )

            launch(Dispatchers.Default) {

                binding.buildingSearchEdittext.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                    }

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                    }

                    override fun afterTextChanged(s: Editable?) {
                        Log.d("SearchFragment3",buildingArray.toString())
                        val searchText = binding.buildingSearchEdittext.text.toString()
                        buildingSearchArray.clear()

                        if(searchText != "") {
                            for(i in 0 until buildingArray.size) {
                                if(buildingArray[i].name.contains(searchText)) {
                                    buildingSearchArray.add(buildingArray[i])
                                }
                            }
                        }
                        Log.d("SearchFragment1", buildingSearchArray.toString())
                    }
                })
                Log.d("SearchFragment2", buildingSearchArray.toString())
            }


            launch(Dispatchers.Main) {
                binding.buildingSearchButton.setOnClickListener {
                    updateView(buildingSearchArray)
                }
            }
        }
    }

    private inner class BuildingSearchHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        private lateinit var building: Building
        private val buildingSearchName : TextView = view.findViewById(R.id.buildingSearch_name)
        private val buildingSearchNumber: TextView = view.findViewById(R.id.buildingSearch_number)

        init {
            view.setOnClickListener(this)
        }

        fun bind(building: Building) {
            this.building = building
            buildingSearchName.text = this.building.name
            buildingSearchNumber.text = this.building.id.toString()
        }

        override fun onClick(v: View?) {
            Log.d("SearchFragmentClick", building.id.toString())
            callbacks?.onSearchBuilding(building.id)
        }
    }

    private inner class BuildingSearchAdapter(var buildingArray: ArrayList<Building>) : RecyclerView.Adapter<BuildingSearchHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuildingSearchHolder {
            val view = layoutInflater.inflate(R.layout.search_building_list_item, parent, false)
            return BuildingSearchHolder(view)
        }

        override fun onBindViewHolder(holder: BuildingSearchHolder, position: Int) {
            val buildings = buildingArray[position]
            holder.bind(buildings)
        }

        override fun getItemCount(): Int {
            return buildingArray.size
        }

    }

    private fun updateView(buildings: ArrayList<Building>) {
        adapter = BuildingSearchAdapter(buildings)
        buildingSearchRecyclerView.adapter = adapter
    }

    companion object {
        fun newInstance() : SearchFragment {
            return SearchFragment()
        }
    }


}