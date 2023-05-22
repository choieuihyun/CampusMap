package com.myproject.campusmap_cleanarchitecture.presentation.adapter.notice

import androidx.recyclerview.widget.RecyclerView
import com.myproject.campusmap_cleanarchitecture.domain.model.NoticeItem
import com.myproject.campusmap_cleanarchitecture.presentation.databinding.NoticeFragmentListItemBinding

class NoticeViewHolder(
    private val binding: NoticeFragmentListItemBinding
) : RecyclerView.ViewHolder(binding.root){

    fun bind(notice: NoticeItem) {

        binding.notice = notice

    }

}