package com.myproject.campusmap_cleanarchitecture.ui.adapter.lectureroomdetail

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.myproject.campusmap_cleanarchitecture.databinding.LectureroomFragmentDetailItemBinding
import com.myproject.campusmap_cleanarchitecture.ui.lectureroom.lectureroomdetail.LectureRoomDetailViewModel
import com.myproject.campusmap_cleanarchitecture.ui.lectureroom.lectureroomdetail.LectureRoomDetailViewPagerItems

class LectureRoomDetailViewPagerViewHolder(
    private val binding: LectureroomFragmentDetailItemBinding,
    private val viewModel: LectureRoomDetailViewModel
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(context: Context, items: LectureRoomDetailViewPagerItems) {
        if (items.path != null) {
            viewModel.getImagesUseCase(context, items.path, binding.lectureRoomDetailImageview)
        }
    }
}