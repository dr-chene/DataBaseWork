package com.viper.databasework.entry

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * created by viper on 2021/6/5
 * desc
 */
@Entity(tableName = "stock", primaryKeys = ["store", "product"])
data class Stock(
    val store: String,
    val product: String,
    val vendor: String,
    val number: Int
)
