package com.viper.databasework.view.activity

import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.addTextChangedListener
import com.viper.databasework.R
import com.viper.databasework.VerticalItemDecoration
import com.viper.databasework.adapter.ProductAdapter
import com.viper.databasework.adapter.StoreAdapter
import com.viper.databasework.base.BaseActivity
import com.viper.databasework.databinding.ActivitySearchBinding
import com.viper.databasework.view.fragment.SortDialogFragment
import com.viper.databasework.viewmodel.ProductViewModel
import com.viper.databasework.viewmodel.SortViewModel
import com.viper.databasework.viewmodel.StoreViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : BaseActivity<ActivitySearchBinding>() {
    private val productAdapter by inject<ProductAdapter>()
    private val storeAdapter by inject<StoreAdapter>()
    private val productViewModel by viewModel<ProductViewModel>()
    private val storeViewModel by viewModel<StoreViewModel>()
    private var searchType = "商品"
    private val sortViewModel by viewModel<SortViewModel>()
    private val sortFragment by inject<SortDialogFragment>()

    override fun onInitView() {
        binding.searchRv.addItemDecoration(VerticalItemDecoration())
        binding.searchTypeProduct.isChecked = true
    }

    override fun onInitAction() {
        binding.searchSearchBar.searchBar.apply {
            requestFocus()
            setOnEditorActionListener { v, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    val key = if (v.text.isNullOrEmpty()) {
                        v.hint
                    } else v.text
                    hideInput()
                    search(searchType, key.toString())
                }
                return@setOnEditorActionListener false
            }
            addTextChangedListener {
                if (it != null && it.toString().isNotBlank()) {
                    binding.searchSearchBar.ivSearchBarCancel.visibility = View.VISIBLE
                } else {
                    binding.searchSearchBar.ivSearchBarCancel.visibility = View.INVISIBLE
                }
            }
        }
        binding.searchType.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.search_type_product -> searchType = "商品"
                R.id.search_type_store -> searchType = "商店"
            }
        }
        binding.searchSearchBar.ivSearchBarCancel.setOnClickListener {
            binding.searchSearchBar.searchBar.setText("")
            onBackPressed()
        }
        binding.searchSearchBar.searchBarIvBack.setOnClickListener {
            finish()
        }
        binding.searchIcSort.setOnClickListener {
            hideInput()
            if (!sortFragment.isAdded) sortFragment.show(supportFragmentManager, "sort")
        }
        binding.searchSrl.setOnRefreshListener {
            hideInput()
            search(searchType, binding.searchSearchBar.searchBar.text.toString())
        }
    }

    override fun getContentViewResId() = R.layout.activity_search

    override fun onSubscribe() {
        productViewModel.products.observe(this) {
            productAdapter.submitList(it)
            binding.searchSrl.isRefreshing = false
            binding.searchEmpty.visibility = if (it.isNotEmpty()) View.GONE else View.VISIBLE
        }
        storeViewModel.stores.observe(this) {
            storeAdapter.submitList(it)
            binding.searchSrl.isRefreshing = false
            binding.searchEmpty.visibility = if (it.isNotEmpty()) View.VISIBLE else View.GONE
        }
        sortViewModel.sort.observe(this) {
            if (it) {
                if (binding.searchRv.adapter is ProductAdapter) {
                    val list = productAdapter.currentList.toMutableList()
                    if (sortViewModel.sortOrder == "asc") {
                        when (sortViewModel.sortKey) {
                            "name" -> list.sortBy { item -> item.name }
                            "brand" -> list.sortBy { item -> item.brand }
                            "price" -> list.sortBy { item -> item.price }
                            "store" -> list.sortBy { item -> item.store }
                            "type" -> list.sortBy { item -> item.type }
                        }
                    } else {
                        when (sortViewModel.sortKey) {
                            "name" -> list.sortByDescending { item -> item.name }
                            "brand" -> list.sortByDescending { item -> item.brand }
                            "price" -> list.sortByDescending { item -> item.price }
                            "store" -> list.sortByDescending { item -> item.store }
                            "type" -> list.sortByDescending { item -> item.type }
                        }
                    }
                    productAdapter.submitList(list)
                } else {
                    val list = storeAdapter.currentList
                    if (sortViewModel.sortOrder == "asc") {
                        list.sortBy { item -> item.name }
                    } else {
                        list.sortByDescending { item -> item.name }
                    }
                    storeAdapter.submitList(list)
                }
            }
        }
    }

    private fun search(type: String, key: String) {
        if (type == "商品") {
            searchProducts(key)
        } else searchStores(key)
    }

    private fun searchProducts(key: String) {
        binding.searchRv.adapter = productAdapter
        productViewModel.search(key)
    }

    private fun searchStores(key: String) {
        binding.searchRv.adapter = storeAdapter
        storeViewModel.search(key)
    }

    private fun hideInput() {
        window.peekDecorView()?.let {
            (getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
                it.windowToken,
                0
            )
        }
    }
}