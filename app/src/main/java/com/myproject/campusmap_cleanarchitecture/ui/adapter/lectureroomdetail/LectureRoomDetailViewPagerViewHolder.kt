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
        if(items.path != null) {
            viewModel.getImagesUseCase(context, items.path, binding.lectureRoomDetailImageview)
        }
        Log.d("glideload", items.path.toString())
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