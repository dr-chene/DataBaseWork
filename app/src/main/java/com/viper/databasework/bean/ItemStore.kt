package com.viper.databasework.bean

import androidx.recyclerview.widget.DiffUtil

/**
 * created by viper on 2021/6/8
 * desc
 */
data class ItemStore(
    val name: String,
    val address: String
)

object StoreDiffCallBack : DiffUtil.ItemCallback<ItemStore>() {
    override fun areItemsTheSame(oldItem: ItemStore, newItem: ItemStore): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: ItemStore, newItem: ItemStore): Boolean {
        return oldItem.address == newItem.address
    }

}