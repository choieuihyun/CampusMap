package com.myproject.campusmap_cleanarchitecture.ui.notice


import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.myproject.campusmap_cleanarchitecture.R
import com.myproject.campusmap_cleanarchitecture.databinding.NoticeFragmentGeneralBinding
import com.myproject.campusmap_cleanarchitecture.domain.model.NoticeItem
import com.myproject.campusmap_cleanarchitecture.ui.BaseFragment
import com.myproject.campusmap_cleanarchitecture.ui.LoadingProgress
import com.myproject.campusmap_cleanarchitecture.ui.adapter.notice.NoticeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoticeGeneralFragment : BaseFragment<NoticeFragmentGeneralBinding>(R.layout.notice_fragment_general) {

//    private val viewModel : NoticeFragmentViewModel by viewModels({requireParentFragment()}) // viewModel을 ParentFragment 기준으로.
    // activityViewModels로 하면 안됨. 뜬거 계속 뜸.
    private val viewModel : NoticeFragmentViewModel by viewModels()
    private lateinit var noticeAdapter: NoticeAdapter
    private lateinit var dialog: Dialog
    private lateinit var noticeSearchArray: ArrayList<NoticeItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dialog = LoadingProgress(requireActivity())
        dialog.show()

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()

        viewModel.getGeneralNotice()

        noticeSearchArray = ArrayList()

        viewModel.notices.observe(viewLifecycleOwner) { notices -> run {

            noticeAdapter.submitList(notices)
            dialog.dismiss()

            binding.searchGeneralEdittext.addTextChangedListener(object : TextWatcher {

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
                    val searchText = binding.searchGeneralEdittext.text.toString()
                    noticeSearchArray.clear()

                        if (searchText != "") {
                            for (i in notices.indices) {
                                if (notices[i].title.contains(searchText)) {
                                    noticeSearchArray.add(notices[i])
                                }
                            }
                        }
                     }
                })
            }
            binding.noticeGeneralSearchButton.setOnClickListener {
                noticeAdapter.submitList(noticeSearchArray.toMutableList()) // toMutableList로 해결.
            }
        }

    }

    private fun setRecyclerView() {
        noticeAdapter = NoticeAdapter()
        binding.noticeGeneralRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            adapter = noticeAdapter
        }

        noticeAdapter.setOnItemClickListener {
            noticeItem ->
            val openUrl = Intent(Intent.ACTION_VIEW, Uri.parse(noticeItem.link))
            openUrl.data = Uri.parse(noticeItem.link)
            startActivity(openUrl)
        }
    }


}