package com.viper.databasework.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.viper.databasework.bean.ItemProduct
import com.viper.databasework.entry.Product

/**
 * created by viper on 2021/6/8
 * desc
 */
@Dao
interface ProductDao {

    @Query("SELECT name, brand, type, store, vendor, price, number FROM product, stock WHERE product.name = stock.product")
    fun getProducts(): List<ItemProduct>

    @Query("SELECT name, brand, type, store, vendor, price, number FROM product, stock WHERE product.name = stock.product AND product.name LIKE '%' || :key || '%'")
    fun searchProducts(key: String): List<ItemProduct>

    @Query("SELECT name, brand, type, store, vendor, price, number FROM product, stock WHERE product.name = stock.product AND store = :store")
    fun searchProductsByStore(store: String): List<ItemProduct>

    @Query("SELECT product FROM buy GROUP BY store ORDER BY COUNT(product) LIMIT 5")
    fun getHotProducts(): List<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(vararg product: Product)
}