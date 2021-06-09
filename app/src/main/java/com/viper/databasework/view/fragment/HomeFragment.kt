package com.viper.databasework.view.fragment

import android.content.Intent
import android.view.View
import androidx.core.view.get
import com.google.android.material.chip.Chip
import com.google.android.material.tabs.TabLayout
import com.viper.databasework.R
import com.viper.databasework.VerticalItemDecoration
import com.viper.databasework.adapter.ProductAdapter
import com.viper.databasework.adapter.StoreAdapter
import com.viper.databasework.base.BaseFragment
import com.viper.databasework.databinding.FragmentHomeBinding
import com.viper.databasework.view.activity.SearchActivity
import com.viper.databasework.viewmodel.ProductViewModel
import com.viper.databasework.viewmodel.StoreViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val productAdapter by inject<ProductAdapter>()
    private val storeAdapter by inject<StoreAdapter>()
    private val productViewModel by viewModel<ProductViewModel>()
    private val storeViewModel by viewModel<StoreViewModel>()
    private var isProduct = true

    override fun onInitView() {
        binding.homeRv.addItemDecoration(VerticalItemDecoration())
        binding.homeSrl.isRefreshing = true
        productViewModel.getHotProducts()
        storeViewModel.getHotStores()
        showProducts()
    }

    override fun onInitAction() {
        binding.homeTabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    when (tab.position) {
                        0 -> showProducts()
                        1 -> showStores()
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
        binding.homeSearchBar.setOnClickListener {
            startActivity(Intent(binding.root.context, SearchActivity::class.java))
        }
        binding.homeSrl.setOnRefreshListener {
            productViewModel.getHotProducts()
            storeViewModel.getHotStores()
            if (isProduct) showProducts()
            else showStores()
        }
    }

    override fun getContentViewResId() = R.layout.fragment_home

    override fun onSubscribe() {
        productViewModel.products.observe(this) {
            productAdapter.submitList(it)
            binding.homeSrl.isRefreshing = false
            binding.homeEmpty.visibility = if(it.isNotEmpty()) View.GONE else View.VISIBLE
        }
        productViewModel.hotProducts.observe(this) {
            if (it.isEmpty()) {
                binding.homeTvHotProduct.visibility = View.GONE
                binding.homeChipsHotProduct.visibility = View.GONE
            } else {
                binding.homeTvHotProduct.visibility = View.VISIBLE
                binding.homeChipsHotProduct.visibility = View.VISIBLE
                binding.apply {
                    it.bindHot(0, homeHotProduct1)
                    it.bindHot(1, homeHotProduct2)
                    it.bindHot(2, homeHotProduct3)
                    it.bindHot(3, homeHotProduct4)
                    it.bindHot(4, homeHotProduct5)
                }
            }
        }
        storeViewModel.stores.observe(this) {
            storeAdapter.submitList(it)
            binding.homeSrl.isRefreshing = false
            binding.homeEmpty.visibility = if(it.isNotEmpty()) View.GONE else View.VISIBLE
        }
        storeViewModel.hotStores.observe(this) {
            if (it.isEmpty()) {
                binding.homeTvHotShop.visibility = View.GONE
                binding.homeChipsHotShop.visibility = View.GONE
            } else {
                binding.homeTvHotShop.visibility = View.VISIBLE
                binding.homeChipsHotShop.visibility = View.VISIBLE
                binding.apply {
                    it.bindHot(0, homeHotStore1)
                    it.bindHot(1, homeHotStore2)
                    it.bindHot(2, homeHotStore3)
                }
            }
        }
    }

    private fun showProducts() {
        isProduct = true
        binding.homeRv.adapter = productAdapter
        productsRefresh()
    }

    private fun productsRefresh() {
        productViewModel.refresh()
    }

    private fun showStores() {
        isProduct = false
        binding.homeRv.adapter = storeAdapter
        storesRefresh()
    }

    private fun storesRefresh() {
        storeViewModel.refresh()
    }

    private fun List<String>.bindHot(pos: Int, chip: Chip) {
        if (pos >= this.size) chip.visibility = View.GONE
        else chip.text = this[pos]
    }
}