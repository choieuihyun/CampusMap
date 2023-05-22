package com.myproject.campusmap_cleanarchitecture.presentation.adapter.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.myproject.campusmap_cleanarchitecture.domain.model.LectureRoom
import com.myproject.campusmap_cleanarchitecture.presentation.databinding.SearchFragmentLectureroomListItemBinding

class SearchLectureRoomAdapter : ListAdapter<LectureRoom, SearchLectureRoomViewHolder>(
    lectureRoomDiffCallback
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchLectureRoomViewHolder {
        return SearchLectureRoomViewHolder(
            SearchFragmentLectureroomListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: SearchLectureRoomViewHolder, position: Int) {
        val lectureRoom = currentList[position]
        holder.bind(lectureRoom)
        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(lectureRoom)
            }
        }
    }

    private var onItemClickListener : ((LectureRoom) -> Unit)? = null

    fun setOnItemClickListener(listener : (LectureRoom) -> Unit) {
        onItemClickListener = listener
    }

    companion object {

        private val lectureRoomDiffCallback = object : DiffUtil.ItemCallback<LectureRoom>() {

            override fun areItemsTheSame(oldItem: LectureRoom, newItem: LectureRoom): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

            override fun areContentsTheSame(oldItem: LectureRoom, newItem: LectureRoom): Boolean {
                return oldItem == newItem
            }

        }
    }

}