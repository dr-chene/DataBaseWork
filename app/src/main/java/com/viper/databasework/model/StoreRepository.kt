package com.viper.databasework.model

/**
 * created by viper on 2021/6/8
 * desc
 */
class StoreRepository(
    private val dao: StoreDao
) {
    fun getStores() = dao.getStores()

    fun searchStores(key: String) = dao.searchStores(key)

    fun getHotStores() = dao.getHotStores()
}