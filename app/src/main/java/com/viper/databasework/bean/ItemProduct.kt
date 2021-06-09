package com.viper.databasework.bean

import androidx.recyclerview.widget.DiffUtil

/**
 * created by viper on 2021/6/7
 * desc
 */
data class ItemProduct(
    val name: String,
    val brand: String,
    val type: String,
    val store: String,
    val vendor: String,
    val price: Int,
    var number: Int
)

object ProductDiffCallBack : DiffUtil.ItemCallback<ItemProduct>() {
    override fun areItemsTheSame(oldItem: ItemProduct, newItem: ItemProduct): Boolean {
        return oldItem.name == newItem.name && oldItem.store == newItem.store
    }

    override fun areContentsTheSame(oldItem: ItemProduct, newItem: ItemProduct): Boolean {
        return oldItem.price == newItem.price
    }

}

data class BuyProduct(
    val name: String,
    val brand: String,
    val type: String,
    val store: String,
    val price: Int,
    var pay: Boolean,
    val id: Int
)

object BuyProductDiffCallBack : DiffUtil.ItemCallback<BuyProduct>() {
    override fun areItemsTheSame(oldItem: BuyProduct, newItem: BuyProduct): Boolean {
        return oldItem.id == oldItem.id
    }

    override fun areContentsTheSame(oldItem: BuyProduct, newItem: BuyProduct): Boolean {
        return oldItem.name == newItem.name && oldItem.store == newItem.store && oldItem.pay == newItem.pay
    }
}