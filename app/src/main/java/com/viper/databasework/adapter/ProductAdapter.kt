package com.viper.databasework.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.viper.databasework.bean.ItemProduct
import com.viper.databasework.bean.ProductDiffCallBack
import com.viper.databasework.databinding.RecycleItemProductBinding
import com.viper.databasework.entry.Buy
import com.viper.databasework.entry.Stock
import com.viper.databasework.model.BuyRepository
import com.viper.databasework.model.StockDao
import com.viper.databasework.showToast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.java.KoinJavaComponent.get
import org.koin.java.KoinJavaComponent.inject

/**
 * created by viper on 2021/6/8
 * desc
 */
class ProductAdapter : ListAdapter<ItemProduct, ProductViewHolder>(ProductDiffCallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            RecycleItemProductBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ProductViewHolder(
    private val binding: RecycleItemProductBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(product: ItemProduct) {
        binding.product = product
        binding.productBtnBuy.setOnClickListener {
            if (product.number > 0) {
                CoroutineScope(Dispatchers.IO).launch {
                    get<BuyRepository>(BuyRepository::class.java).buy(
                        Buy(
                            product.number,
                            product.name,
                            product.store,
                            false
                        )
                    )
                    product.number -= 1
                    inject<StockDao>(StockDao::class.java).value.updateStock(product.toStock())
                    "购买成功".showToast()
                    withContext(Dispatchers.Main) {
                        binding.product = product
                        binding.executePendingBindings()
                    }
                }
            } else "库存不足".showToast()
        }
        binding.executePendingBindings()
    }
}

fun ItemProduct.toStock() = Stock(store, name, vendor, number)