package com.myproject.campusmap_cleanarchitecture.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.myproject.campusmap_cleanarchitecture.R
import com.myproject.campusmap_cleanarchitecture.databinding.LectureroomFragmentDetailItemBinding
import com.myproject.campusmap_cleanarchitecture.domain.usecase.GetLectureRoomDetailImagesUseCase
import com.myproject.campusmap_cleanarchitecture.ui.lectureroom.lectureroomdetail.LectureRoomDetailViewModel
import com.myproject.campusmap_cleanarchitecture.ui.lectureroom.lectureroomdetail.LectureRoomDetailViewPagerItems

class LectureRoomDetailViewPagerAdapter(private val context: Context,
                                        private val viewModel: LectureRoomDetailViewModel
) : ListAdapter<LectureRoomDetailViewPagerItems, LectureRoomDetailViewPagerViewHolder>(
    lectureRoomDetailDiffCallback) {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): LectureRoomDetailViewPagerViewHolder {
        return LectureRoomDetailViewPagerViewHolder(LectureroomFragmentDetailItemBinding.inflate(
            LayoutInflater.from(parent.context),parent, false), viewModel
        )
    }

    override fun onBindViewHolder(holder: LectureRoomDetailViewPagerViewHolder, position: Int) {
        val lectureRoomDetail = currentList[position]
/*        holder.apply {
            Glide.with(context).load(lectureRoomDetail.path).into()
        }*/
        holder.bind(context, lectureRoomDetail)
    }

    companion object {

        private val lectureRoomDetailDiffCallback = object : DiffUtil.ItemCallback<LectureRoomDetailViewPagerItems>() {
            override fun areItemsTheSame(oldItem: LectureRoomDetailViewPagerItems, newItem: LectureRoomDetailViewPagerItems): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

            override fun areContentsTheSame(oldItem: LectureRoomDetailViewPagerItems, newItem: LectureRoomDetailViewPagerItems): Boolean {
                return oldItem == newItem
            }

        }
    }


}