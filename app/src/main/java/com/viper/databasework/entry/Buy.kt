package com.viper.databasework.entry

import androidx.room.Entity

/**
 * created by viper on 2021/6/5
 * desc
 */
@Entity(tableName = "buy", primaryKeys = ["product", "store", "id"])
data class Buy(
    val id: Int,
    val product: String,
    val store: String,
    val pay: Boolean
)
