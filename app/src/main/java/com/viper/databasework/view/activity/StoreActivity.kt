package com.viper.databasework.view.activity

import android.view.View
import com.viper.databasework.R
import com.viper.databasework.VerticalItemDecoration
import com.viper.databasework.adapter.ProductAdapter
import com.viper.databasework.base.BaseActivity
import com.viper.databasework.bean.ItemStore
import com.viper.databasework.databinding.ActivityStoreBinding
import com.viper.databasework.viewmodel.StoreProductsViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class StoreActivity : BaseActivity<ActivityStoreBinding>() {
    private val productAdapter by inject<ProductAdapter>()
    private val storeProductViewModel by viewModel<StoreProductsViewModel>()
    private var name = "出错了"

    override fun onInitView() {
        intent.apply {
            name = getStringExtra("name") ?: "出错了"
            val address = getStringExtra("address") ?: "出错了"
            binding.store = ItemStore(name, address)
        }
        binding.storeRv.addItemDecoration(VerticalItemDecoration())
        binding.storeRv.adapter = productAdapter
        binding.storeSrl.isRefreshing = true
        storeProductViewModel.getStoreProducts(name)
    }

    override fun onInitAction() {
        binding.storeSrl.setOnRefreshListener {
            storeProductViewModel.getStoreProducts(name)
        }
    }

    override fun getContentViewResId() = R.layout.activity_store

    override fun onSubscribe() {
        storeProductViewModel.storeProducts.observe(this) {
            productAdapter.submitList(it)
            binding.storeSrl.isRefreshing = false
            binding.storeEmpty.visibility = if(it.isNotEmpty()) View.GONE else View.VISIBLE
        }
    }
}