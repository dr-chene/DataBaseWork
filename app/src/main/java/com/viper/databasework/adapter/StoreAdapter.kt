package com.viper.databasework.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.viper.databasework.bean.ItemStore
import com.viper.databasework.bean.StoreDiffCallBack
import com.viper.databasework.databinding.RecycleItemStoreBinding
import com.viper.databasework.view.activity.StoreActivity

/**
 * created by viper on 2021/6/8
 * desc
 */
class StoreAdapter : ListAdapter<ItemStore, StoreViewHolder>(StoreDiffCallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        return StoreViewHolder(
            RecycleItemStoreBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class StoreViewHolder(
    private val binding: RecycleItemStoreBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(store: ItemStore) {
        binding.store = store
        binding.root.setOnClickListener {
            Intent(binding.root.context, StoreActivity::class.java).apply {
                putExtra("name", store.name)
                putExtra("address", store.address)
                binding.root.context.startActivity(this)
            }
        }
        binding.executePendingBindings()
    }
}