package com.viper.databasework.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ObservableInt
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.viper.databasework.bean.BuyProduct
import com.viper.databasework.bean.BuyProductDiffCallBack
import com.viper.databasework.bean.ItemProduct
import com.viper.databasework.bean.ProductDiffCallBack
import com.viper.databasework.databinding.RecycleItemProductCartBinding

/**
 * created by viper on 2021/6/8
 * desc
 */
class ProductCartAdapter(
    val amount: ObservableInt
) : ListAdapter<BuyProduct, ProductCartViewHolder>(BuyProductDiffCallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductCartViewHolder {
        return ProductCartViewHolder(
            RecycleItemProductCartBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), amount
        )
    }

    override fun onBindViewHolder(holder: ProductCartViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ProductCartViewHolder(
    private val binding: RecycleItemProductCartBinding,
    private val amount: ObservableInt
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(product: BuyProduct) {
        binding.product = product
        binding.recycleProductCartSelect.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                amount.set(amount.get() + product.price)
            } else amount.set(amount.get() - product.price)
            product.pay = isChecked
        }
        binding.executePendingBindings()
    }
}