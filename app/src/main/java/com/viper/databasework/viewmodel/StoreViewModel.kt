package com.viper.databasework.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.viper.databasework.bean.ItemStore
import com.viper.databasework.model.StoreRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * created by viper on 2021/6/8
 * desc
 */
class StoreViewModel(
    private val repository: StoreRepository
) : ViewModel() {
    val stores: LiveData<List<ItemStore>>
        get() = _stores
    private val _stores = MutableLiveData<List<ItemStore>>()

    val hotStores: LiveData<List<String>>
        get() = _hotStores
    private val _hotStores = MutableLiveData<List<String>>()

    fun refresh() {
        CoroutineScope(Dispatchers.IO).launch {
            repository.getStores().let {
                withContext(Dispatchers.Main) {
                    _stores.postValue(it)
                }
            }
        }
    }

    fun search(key: String) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.searchStores(key).let {
                withContext(Dispatchers.Main) {
                    _stores.postValue(it)
                }
            }
        }
    }

    fun getHotStores() {
        CoroutineScope(Dispatchers.IO).launch {
            repository.getHotStores().let {
                withContext(Dispatchers.Main) {
                    _hotStores.postValue(it)
                }
            }
        }
    }
}