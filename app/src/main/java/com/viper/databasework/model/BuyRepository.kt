package com.viper.databasework.model

import com.viper.databasework.entry.Buy

/**
 * created by viper on 2021/6/8
 * desc
 */
class BuyRepository(
    private val dao: BuyDao
) {
    fun getBuy() = dao.getBuy()

    fun getNotBuy() = dao.getNotBuy()

    suspend fun pay(buy: Buy) = dao.updateBuy(buy)

    suspend fun buy(buy: Buy) = dao.insertBuy(buy)
}