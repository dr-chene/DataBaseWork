package com.viper.databasework.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.viper.databasework.bean.ItemProduct
import com.viper.databasework.model.ProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * created by viper on 2021/6/8
 * desc
 */
class ProductViewModel(
    private val repository: ProductRepository
) : ViewModel() {

    val products: LiveData<List<ItemProduct>>
        get() = _products
    private val _products = MutableLiveData<List<ItemProduct>>()

    val hotProducts: LiveData<List<String>>
        get() = _hotProducts
    private val _hotProducts = MutableLiveData<List<String>>()

    fun refresh() {
        CoroutineScope(Dispatchers.IO).launch {
            repository.refresh().let {
                withContext(Dispatchers.Main) {
                    _products.postValue(it)
                }
            }
        }
    }

    fun search(key: String) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.search(key).let {
                withContext(Dispatchers.Main) {
                    _products.postValue(it)
                }
            }
        }
    }

    fun getHotProducts() {
        CoroutineScope(Dispatchers.IO).launch {
            repository.getHotProducts().let {
                withContext(Dispatchers.Main) {
                    _hotProducts.postValue(it)
                }
            }
        }
    }
}