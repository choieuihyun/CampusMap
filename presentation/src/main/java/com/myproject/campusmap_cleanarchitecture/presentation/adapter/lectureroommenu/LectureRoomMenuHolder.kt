package com.myproject.campusmap_cleanarchitecture.presentation.adapter.lectureroommenu

import androidx.recyclerview.widget.RecyclerView
import com.myproject.campusmap_cleanarchitecture.domain.model.LectureRoom
import com.myproject.campusmap_cleanarchitecture.presentation.databinding.LectureroomListItemBinding

class LectureRoomMenuHolder(
    private val binding: LectureroomListItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(lectureRoom: LectureRoom) {
        binding.lectureRoomItem = lectureRoom
    }

}