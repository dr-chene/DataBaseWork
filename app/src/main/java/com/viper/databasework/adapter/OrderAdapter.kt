package com.viper.databasework.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import com.viper.databasework.databinding.RecycleItemOrderBinding

/**
 * created by viper on 2021/6/8
 * desc
 */
//class OrderAdapter : ListAdapter<ItemOrder, OrderViewHolder>(OrderDiffCallBack) {
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
//        return OrderViewHolder(
//            RecycleItemOrderBinding.inflate(
//                LayoutInflater.from(parent.context), parent, false
//            )
//        )
//    }
//
//    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
//        holder.bind(getItem(position))
//    }
//}
//
//class OrderViewHolder(
//    private val binding: RecycleItemOrderBinding
//) : RecyclerView.ViewHolder(binding.root) {
//
//    fun bind(order: ItemOrder) {
//        binding.order = order
//        binding.executePendingBindings()
//    }
//}