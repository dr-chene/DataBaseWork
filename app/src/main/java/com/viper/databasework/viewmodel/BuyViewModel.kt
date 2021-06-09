package com.viper.databasework.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.viper.databasework.bean.BuyProduct
import com.viper.databasework.entry.Buy
import com.viper.databasework.model.BuyRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * created by viper on 2021/6/8
 * desc
 */
class BuyViewModel(
    private val repository: BuyRepository
) : ViewModel() {
    val products: LiveData<List<BuyProduct>>
        get() = _products
    private val _products = MutableLiveData<List<BuyProduct>>()

    val notBuy: LiveData<List<BuyProduct>>
        get() = _notBuy
    private val _notBuy = MutableLiveData<List<BuyProduct>>()

    fun showNotBuy() {
        CoroutineScope(Dispatchers.IO).launch {
            repository.getNotBuy().let {
                withContext(Dispatchers.Main) {
                    _notBuy.postValue(it)
                }
            }
        }
    }

    fun showBuy() {
        CoroutineScope(Dispatchers.IO).launch {
            repository.getBuy().let {
                withContext(Dispatchers.Main) {
                    _products.postValue(it)
                }
            }
        }
    }

    fun pay(products: List<BuyProduct>) {
        CoroutineScope(Dispatchers.IO).launch {
            products.map {
                Buy(it.id, it.name, it.store, true)
            }.forEach {
                repository.pay(it)
            }
        }
    }
}