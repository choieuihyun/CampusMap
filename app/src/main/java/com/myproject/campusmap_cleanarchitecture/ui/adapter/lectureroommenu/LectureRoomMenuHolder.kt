package com.myproject.campusmap_cleanarchitecture.ui.adapter.lectureroommenu

import androidx.recyclerview.widget.RecyclerView
import com.myproject.campusmap_cleanarchitecture.databinding.LectureroomListItemBinding
import com.myproject.campusmap_cleanarchitecture.domain.model.LectureRoom

class LectureRoomMenuHolder(
    private val binding: LectureroomListItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(lectureRoom: LectureRoom) {
        binding.lectureRoomItem = lectureRoom
    }

}