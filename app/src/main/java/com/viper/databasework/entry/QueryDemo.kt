package com.viper.databasework.entry

import androidx.room.Dao
import androidx.room.Query

/**
 * created by viper on 2021/6/5
 * desc
 */
@Dao
interface QueryDemo {

//    //每家商店最畅销的20种产品？
//    @Query("SELECT product FROM cart, buy WHERE cart.id = buy.id GROUP BY store, product ORDER BY COUNT(product) LIMIT 20")
//    fun query1()
//
//    //每个州最畅销的20种产品？
//    @Query("SELECT address, product FROM cart, buy, store WHERE cart.id = buy.id AND name = store GROUP BY address, product ORDER BY COUNT(product) LIMIT 20")
//    fun query2()
//
//    //今年到目前为止销量最多的5家门店？
//    @Query("SELECT store FROM cart WHERE time - :time <= 31536000000 GROUP BY store ORDER BY COUNT(amount) LIMIT 5")
//    fun query3(time: Long)
//
//    //可口可乐在多少家商店的销量超过了百事可乐？
//    @Query("SELECT store FROM cart as out_o GROUP BY store HAVING (SELECT COUNT(*) FROM cart as in_o,buy WHERE out_o.store = in_o.store AND in_o.id = buy.id AND buy.product = '可口可乐') > (SELECT COUNT(*) FROM s_order as in_o,buy WHERE out_o.store = in_o.store AND in_o.id = buy.id AND buy.product = '百事可乐')")
//    fun query4()
//
//    //除了牛奶，顾客还购买的前三种产品是什么？
//    @Query("SELECT type FROM product, s_order, buy WHERE type != '牛奶' AND s_order.id = buy.id AND buy.product = product.name GROUP BY type ORDER BY COUNT(type) LIMIT 3")
//    fun query5()

}