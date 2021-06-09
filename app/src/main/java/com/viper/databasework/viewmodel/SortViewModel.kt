package com.viper.databasework.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * created by viper on 2021/6/8
 * desc
 */
class SortViewModel : ViewModel() {
    @Volatile
    var sortKey = "name"

    @Volatile
    var sortOrder = "asc"

    val sort: LiveData<Boolean>
        get() = _sort
    private val _sort = MutableLiveData<Boolean>()

    fun sort() {
        _sort.postValue(true)
    }

    fun unSort() {
        _sort.postValue(false)
    }
}