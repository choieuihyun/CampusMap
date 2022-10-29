package com.myproject.campusmap_cleanarchitecture.ui.notice

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.myproject.campusmap_cleanarchitecture.R
import com.myproject.campusmap_cleanarchitecture.databinding.NoticeFragmentJanghakBinding
import com.myproject.campusmap_cleanarchitecture.ui.BaseFragment
import com.myproject.campusmap_cleanarchitecture.ui.LoadingProgress
import com.myproject.campusmap_cleanarchitecture.ui.adapter.NoticeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class NoticeJanghakFragment : BaseFragment<NoticeFragmentJanghakBinding>(R.layout.notice_fragment_janghak) {

    private val viewModel : NoticeFragmentViewModel by viewModels()
    private lateinit var noticeAdapter: NoticeAdapter
    private lateinit var dialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dialog = LoadingProgress(requireActivity())
        dialog.show()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()


        viewModel.getJanghakNotice()

        viewModel.notices.observe(viewLifecycleOwner) {
                notices -> noticeAdapter.submitList(notices)
            dialog.dismiss()
        }


    }

    private fun setRecyclerView() {
        noticeAdapter = NoticeAdapter()
        binding.noticeJanghakRecyclerView.apply {
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