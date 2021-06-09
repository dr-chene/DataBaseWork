package com.viper.databasework.entry

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * created by viper on 2021/6/5
 * desc
 */
@Entity(tableName = "brand")
data class Brand(
    @PrimaryKey
    val name: String,
    val enterprise: String
)