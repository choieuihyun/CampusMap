package com.myproject.campusmap_cleanarchitecture.ui.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.myproject.campusmap_cleanarchitecture.databinding.LectureroomFragmentDetailItemBinding
import com.myproject.campusmap_cleanarchitecture.ui.lectureroom.lectureroomdetail.LectureRoomDetailViewModel
import com.myproject.campusmap_cleanarchitecture.ui.lectureroom.lectureroomdetail.LectureRoomDetailViewPagerItems

class LectureRoomDetailViewPagerViewHolder(
    private val binding: LectureroomFragmentDetailItemBinding,
    private val viewModel: LectureRoomDetailViewModel
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(context: Context, items: LectureRoomDetailViewPagerItems) {
        viewModel.getImagesUseCase(context, items.path, binding.lectureRoomDetailImageview)
    }

/*    fun bind(context: Context, items: LectureRoomDetailViewPagerItems) {
        Glide.with(context).load(items.path).into(binding.lectureRoomDetailImageview)
        Log.d("glideload", items.path.toString())
    }*/

/*    fun bind(items: LectureRoomDetailViewPagerItems) {
        var image: ImageView = binding.lectureRoomDetailImageview
        image = items.imageView
    }*/

}