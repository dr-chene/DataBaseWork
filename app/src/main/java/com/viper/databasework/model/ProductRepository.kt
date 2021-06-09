package com.viper.databasework.model

/**
 * created by viper on 2021/6/8
 * desc
 */
class ProductRepository(
    private val dao: ProductDao
) {

    fun refresh() = dao.getProducts()

    fun search(key: String) = dao.searchProducts(key)

    fun getHotProducts() = dao.getHotProducts()

    fun searchByStore(store: String) = dao.searchProductsByStore(store)
}