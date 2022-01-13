package com.example.test1.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.test1.adapters.Adapter.DataViewHolder
import com.example.test1.adapters.Adapter.DataViewHolder.DataComparator
import com.example.test1.databinding.DataItemBinding
import com.example.test1.domain.ApiData

class Adapter : ListAdapter<ApiData, DataViewHolder>(DataComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = DataItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    class DataViewHolder(private val binding: DataItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ApiData) {
            binding.apply {
                this.data = data
            }
        }

        class DataComparator : DiffUtil.ItemCallback<ApiData>() {
            override fun areItemsTheSame(oldItem: ApiData, newItem: ApiData) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ApiData, newItem: ApiData) =
                oldItem == newItem
        }
    }
}