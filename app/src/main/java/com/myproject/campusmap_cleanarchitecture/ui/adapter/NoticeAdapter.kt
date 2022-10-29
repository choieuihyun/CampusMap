package com.myproject.campusmap_cleanarchitecture.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.myproject.campusmap_cleanarchitecture.databinding.NoticeFragmentListItemBinding

import com.myproject.campusmap_cleanarchitecture.domain.model.NoticeItem

class NoticeAdapter() : ListAdapter<NoticeItem, NoticeViewHolder>(NoticeDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder {
        return NoticeViewHolder(
            NoticeFragmentListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        val notice = currentList[position]
        holder.bind(notice)
        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(notice)
            }
        }
    }

    private var onItemClickListener: ((NoticeItem) -> Unit)? = null

    fun setOnItemClickListener(listener: (NoticeItem) -> Unit) {
        onItemClickListener = listener
    }


    companion object {

        private val NoticeDiffCallback = object : DiffUtil.ItemCallback<NoticeItem>() {
            override fun areItemsTheSame(oldItem: NoticeItem, newItem: NoticeItem): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

            override fun areContentsTheSame(oldItem: NoticeItem, newItem: NoticeItem): Boolean {
                return oldItem == newItem
            }

        }
    }



}