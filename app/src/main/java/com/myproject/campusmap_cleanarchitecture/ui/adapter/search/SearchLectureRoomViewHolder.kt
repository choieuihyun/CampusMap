package com.myproject.campusmap_cleanarchitecture.ui.adapter.search

import androidx.recyclerview.widget.RecyclerView
import com.myproject.campusmap_cleanarchitecture.databinding.SearchFragmentLectureroomListItemBinding
import com.myproject.campusmap_cleanarchitecture.domain.model.LectureRoom

class SearchLectureRoomViewHolder(
    private val binding: SearchFragmentLectureroomListItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(lectureRoom: LectureRoom) {

        binding.lectureRoom = lectureRoom

    }

}