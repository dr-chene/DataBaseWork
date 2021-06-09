package com.viper.databasework.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.viper.databasework.bean.ItemStore
import com.viper.databasework.entry.Store

/**
 * created by viper on 2021/6/8
 * desc
 */
@Dao
interface StoreDao {

    @Query("SELECT * FROM store")
    fun getStores(): List<ItemStore>

    @Query("SELECT * FROM store WHERE name LIKE '%' || :key || '%'")
    fun searchStores(key: String): List<ItemStore>

    @Query("SELECT store FROM buy GROUP BY store ORDER BY COUNT(product) LIMIT 3")
    fun getHotStores(): List<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStore(vararg store: Store)
}