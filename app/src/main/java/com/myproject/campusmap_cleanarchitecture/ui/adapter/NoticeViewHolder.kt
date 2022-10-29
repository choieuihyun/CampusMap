package com.myproject.campusmap_cleanarchitecture.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.myproject.campusmap_cleanarchitecture.databinding.NoticeFragmentListItemBinding
import com.myproject.campusmap_cleanarchitecture.domain.model.NoticeItem

class NoticeViewHolder(
    private val binding: NoticeFragmentListItemBinding
) : RecyclerView.ViewHolder(binding.root){

    fun bind(notice: NoticeItem) {

        binding.notice = notice

    }

}