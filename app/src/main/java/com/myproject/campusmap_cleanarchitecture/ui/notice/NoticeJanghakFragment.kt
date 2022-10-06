package com.campusmap.android.campusmap_with_kakao.notice

import android.app.Dialog
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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.campusmap.android.campusmap_with_kakao.LoadingProgress
import com.campusmap.android.campusmap_with_kakao.MainActivity
import com.campusmap.android.campusmap_with_kakao.R
import com.campusmap.android.campusmap_with_kakao.databinding.NoticeJanghakFragmentBinding
import com.campusmap.android.campusmap_with_kakao.jsoup.NoticeItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

class NoticeJanghakFragment : Fragment() {

    private lateinit var binding: NoticeJanghakFragmentBinding
    private lateinit var noticeJanghakRecyclerView: RecyclerView
    private lateinit var docArray: ArrayList<Document>
    private lateinit var noticeDocArray: ArrayList<Elements>
    private lateinit var noticeArray: ArrayList<NoticeItem>
    private lateinit var noticeSearchArray: ArrayList<NoticeItem>
    private lateinit var dialog: Dialog
    private var adapter: NoticeAdapter? = NoticeAdapter(ArrayList())
    private lateinit var mainActivity: MainActivity

    private lateinit var callbacks: Callbacks

    private val noticeJanghakListViewModel: NoticeFragmentViewModel by lazy {
        ViewModelProvider(this).get(NoticeFragmentViewModel::class.java)
    }

    interface Callbacks {
        fun onNoticeClicked(link: String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
        callbacks = mainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        noticeArray = ArrayList(emptyList())
        docArray = ArrayList(emptyList())
        noticeDocArray = ArrayList(emptyList())
        noticeSearchArray = ArrayList(emptyList())

        dialog = LoadingProgress(mainActivity)
        dialog.show()

        CoroutineScope(Dispatchers.IO).launch { // 네트워크같은 작업이 IO

            noticeJanghakListViewModel.loadJanghakNoticeItems()

            noticeArray = noticeJanghakListViewModel.getNoticeItems

            Log.d("notice", noticeDocArray.toString())

            launch(Dispatchers.Default) { // 자세히는 모르겠는데 이거 되고난 후에 ViewModel이 켜짐 loadGeneralNoticeItems에 join을 안붙이면. 그래서 바로 아래 binding에 lateinit 문제가 뜸.

                binding.searchJanghakEdittext.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {

                    }

                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {

                    }

                    override fun afterTextChanged(s: Editable?) {
                        val searchText = binding.searchJanghakEdittext.text.toString()
                        noticeSearchArray.clear()

                        if (searchText != "") {
                            for (i in 0 until noticeArray.size) {
                                if (noticeArray[i].title.contains(searchText)) {
                                    noticeSearchArray.add(noticeArray[i])
                                }
                            }
                        }
                        Log.d("NoticeFragment8", noticeSearchArray.toString())
                        Log.d("NoticeFragment8", noticeSearchArray.size.toString())
                    }
                })
                Log.d("NoticeFragment11", noticeSearchArray.toString())
            }

            launch(Dispatchers.Main) { // Main은 UI겠지 뭐 UI를 Main Thread에서만 하니까. 근데 이렇게 하니까
                // ViewCreated 같은 원래 lifecycle이 먼저 적용되기때문에 E/RecyclerView: No adapter attached; skipping layout 이게 계속 뜨는것 같은데 어떻게 해야하지..
                updateView(noticeArray)
                dialog.dismiss()
                binding.noticeJanghakSearchButton.setOnClickListener {
                    Log.d("NoticeFragment9", noticeSearchArray.toString())
                    Log.d("NoticeFragment9", noticeSearchArray.size.toString())
                    updateView(noticeSearchArray)
                }
//                Log.d("NoticeFragment4", returnValue.await().toString())
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.notice_janghak_fragment, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noticeJanghakRecyclerView = view.findViewById(R.id.noticeJanghak_RecyclerView) as RecyclerView
        noticeJanghakRecyclerView.layoutManager = LinearLayoutManager(context)
        noticeJanghakRecyclerView.adapter = adapter



    }

    private inner class NoticeHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        private lateinit var notices: NoticeItem
        private val noticeNumber: TextView = view.findViewById(R.id.notice_number)
        private val noticeTitle: TextView = view.findViewById(R.id.notice_title)
        private val noticeInfo: TextView = view.findViewById(R.id.notice_info)

        init {
            view.setOnClickListener(this) // 이게 있어야 Log가 뜨네. 즉, 클릭이 되네
        }

        fun bind(notice: NoticeItem) {
            this.notices = notice
            noticeNumber.text = this.notices.number
            noticeTitle.text = this.notices.title
            noticeInfo.text = this.notices.info
        }

        override fun onClick(v: View?) {
            callbacks.onNoticeClicked(notices.link) // 이거 MainActivity에서 돌리려하면 안됨 왜그러지?
        }

    }

    private inner class NoticeAdapter(var noticeArray: ArrayList<NoticeItem>) : RecyclerView.Adapter<NoticeHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeHolder {
            val view = layoutInflater.inflate(R.layout.notice_list_item, parent, false)
            return NoticeHolder(view)
        }

        override fun onBindViewHolder(holder: NoticeHolder, position: Int) {
            val notices = noticeArray[position]
            holder.bind(notices)
        }

        override fun getItemCount(): Int {
            return noticeArray.size
        }


    }

    private fun updateView(noticeItems: ArrayList<NoticeItem>) {
        adapter = NoticeAdapter(noticeItems)
        noticeJanghakRecyclerView.adapter = adapter
    }

    companion object {

        fun newInstance(): NoticeJanghakFragment {
            return NoticeJanghakFragment()
        }

    }

}