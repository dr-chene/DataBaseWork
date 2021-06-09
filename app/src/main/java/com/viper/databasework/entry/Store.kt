package com.viper.databasework.entry

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * created by viper on 2021/6/5
 * desc
 */
@Entity(tableName = "store")
data class Store(
    @PrimaryKey
    val name: String,
    val address: String
)
