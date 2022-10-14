package com.myproject.campusmap_cleanarchitecture.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.myproject.campusmap_cleanarchitecture.databinding.LectureroomListItemBinding
import com.myproject.campusmap_cleanarchitecture.domain.model.Building
import com.myproject.campusmap_cleanarchitecture.domain.model.EngFirstLectureRoom
import com.myproject.campusmap_cleanarchitecture.domain.model.LectureRoom

class LectureRoomMenuAdapter() : ListAdapter<LectureRoom, LectureRoomMenuHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LectureRoomMenuHolder {
        return LectureRoomMenuHolder(
            LectureroomListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: LectureRoomMenuHolder, position: Int) {
        val lectureRoom = currentList[position]
        holder.bind(lectureRoom)
        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(lectureRoom) }
        }
    }

    private var onItemClickListener: ((LectureRoom) -> Unit)? = null

    fun setOnItemClickListener(listener: (LectureRoom) -> Unit) {
        onItemClickListener = listener
    }

    companion object {

        private val diffCallback = object : DiffUtil.ItemCallback<LectureRoom>() {
            override fun areItemsTheSame(oldItem: LectureRoom, newItem: LectureRoom): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

            override fun areContentsTheSame(oldItem: LectureRoom, newItem: LectureRoom): Boolean {
                return oldItem == newItem
            }

        }
    }


}