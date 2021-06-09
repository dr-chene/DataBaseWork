package com.viper.databasework.model

import androidx.room.*
import com.viper.databasework.bean.BuyProduct
import com.viper.databasework.entry.Buy

/**
 * created by viper on 2021/6/8
 * desc
 */
@Dao
interface BuyDao {

    @Query("SELECT name, brand, type, store, price, pay, id FROM product, buy WHERE NOT buy.pay AND product.name = buy.product")
    fun getNotBuy(): List<BuyProduct>

    @Query("SELECT name, brand, type, store, price, pay, id FROM product, buy WHERE buy.pay AND product.name = buy.product")
    fun getBuy(): List<BuyProduct>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBuy(buy: Buy)

    @Update
    suspend fun updateBuy(buy: Buy)
}