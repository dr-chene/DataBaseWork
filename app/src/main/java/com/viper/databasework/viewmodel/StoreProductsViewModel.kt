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
 * created by viper on 2021/6/9
 * desc
 */
class StoreProductsViewModel(
    private val repository: ProductRepository
) : ViewModel() {
    val storeProducts: LiveData<List<ItemProduct>>
        get() = _storeProducts
    private val _storeProducts = MutableLiveData<List<ItemProduct>>()

    fun getStoreProducts(store: String) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.searchByStore(store).let {
                withContext(Dispatchers.Main) {
                    _storeProducts.postValue(it)
                }
            }
        }
    }
}