package com.viper.databasework.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.viper.databasework.entry.Stock

/**
 * created by viper on 2021/6/8
 * desc
 */
@Dao
interface StockDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStock(vararg stock: Stock)

    @Update
    suspend fun updateStock(stock: Stock)
}