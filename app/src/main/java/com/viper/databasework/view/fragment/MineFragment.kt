package com.viper.databasework.view.fragment

import android.view.View
import androidx.core.view.children
import androidx.databinding.ObservableInt
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.tabs.TabLayout
import com.viper.databasework.R
import com.viper.databasework.VerticalItemDecoration
import com.viper.databasework.adapter.ProductCartAdapter
import com.viper.databasework.base.BaseFragment
import com.viper.databasework.bean.BuyProduct
import com.viper.databasework.databinding.FragmentMineBinding
import com.viper.databasework.showToast
import com.viper.databasework.viewmodel.BuyViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MineFragment : BaseFragment<FragmentMineBinding>() {
    private val productCartAdapter by inject<ProductCartAdapter> { parametersOf(amount) }
    private val buyViewModel by viewModel<BuyViewModel>()
    private val amount = ObservableInt(0)
    private var isNotBuy = true

    override fun onInitView() {
        binding.mineRv.addItemDecoration(VerticalItemDecoration())
        binding.mineRv.adapter = productCartAdapter
        binding.homeBottomBuy.amount = amount
        binding.mineSrl.isRefreshing = true
        showNotBuy()
    }

    override fun onInitAction() {
        binding.mineTabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    when (tab.position) {
                        0 -> showNotBuy()
                        1 -> showBuy()
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
        binding.homeBottomBuy.includeMineBuy.setOnClickListener {
            pay(productCartAdapter.currentList.filter { product -> product.pay })
            amount.set(0)
            binding.homeBottomBuy.includeMineSelectAll.isChecked = false
            "购买成功".showToast()
            showNotBuy()
        }
        binding.homeBottomBuy.includeMineSelectAll.setOnCheckedChangeListener { _, isChecked ->
            binding.mineRv.children.forEach { view ->
                view.findViewById<MaterialCheckBox>(R.id.recycle_product_cart_select).isChecked =
                    isChecked
            }
        }
        binding.mineSrl.setOnRefreshListener {
            if (isNotBuy) showNotBuy()
            else showBuy()
        }
    }

    override fun getContentViewResId() = R.layout.fragment_mine

    override fun onSubscribe() {
        buyViewModel.products.observe(this) {
            productCartAdapter.submitList(it)
            binding.mineSrl.isRefreshing = false
            binding.mineEmpty.visibility = if(it.isNotEmpty()) View.GONE else View.VISIBLE
        }
        buyViewModel.notBuy.observe(this) {
            productCartAdapter.submitList(it)
            binding.mineSrl.isRefreshing = false
            binding.mineEmpty.visibility = if(it.isNotEmpty()) View.GONE else View.VISIBLE
        }
    }

    private fun showNotBuy() {
        isNotBuy = true
        binding.homeBottomBuy.root.visibility = View.VISIBLE
        buyViewModel.showNotBuy()
    }

    private fun showBuy() {
        isNotBuy = false
        binding.homeBottomBuy.root.visibility = View.GONE
        buyViewModel.showBuy()
    }

    private fun pay(products: List<BuyProduct>) {
        buyViewModel.pay(products)
    }
}