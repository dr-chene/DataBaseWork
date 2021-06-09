package com.viper.databasework.entry

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * created by viper on 2021/6/5
 * desc
 */
@Entity(tableName = "product")
data class Product(
    @PrimaryKey
    val name: String,
    val brand: String,
    val type: String,
    val price: Int
)
