package com.myproject.campusmap_cleanarchitecture.presentation.adapter.search

import androidx.recyclerview.widget.RecyclerView
import com.myproject.campusmap_cleanarchitecture.domain.model.LectureRoom
import com.myproject.campusmap_cleanarchitecture.presentation.databinding.SearchFragmentLectureroomListItemBinding

class SearchLectureRoomViewHolder(
    private val binding: SearchFragmentLectureroomListItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(lectureRoom: LectureRoom) {

        binding.lectureRoom = lectureRoom

    }

}