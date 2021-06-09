package com.viper.databasework

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.viper.databasework.entry.Product
import com.viper.databasework.entry.Stock
import com.viper.databasework.entry.Store
import com.viper.databasework.model.ProductDao
import com.viper.databasework.model.StockDao
import com.viper.databasework.model.StoreDao
import kotlinx.coroutines.coroutineScope
import org.koin.java.KoinJavaComponent.inject
import kotlin.random.Random

/**
 * created by viper on 2021/6/8
 * desc
 */
class InitDatabaseWorker(
    context: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {
    private val r = Random(System.currentTimeMillis())
    private val products = listOf(
        Product("蒙牛纯牛奶", "蒙牛", "乳制品", 5),
        Product("百事可乐", "百事", "饮料类", 4),
        Product("百事可乐-乌龙茶味", "百事可乐", "饮料类", 5),
        Product("可口可乐", "可口可乐", "饮料类", 4),
        Product("雀巢咖啡", "雀巢", "饮料类", 6),
        Product("雀巢优活", "雀巢", "饮料类", 4),
        Product("超启能恩", "雀巢", "乳制品", 275),
        Product("金典纯牛奶", "伊利", "乳制品", 92),
        Product("安慕希", "伊利", "乳制品", 78),
        Product("香辣牛肉面", "康师傅", "粮食制品", 6),
        Product("汤大师", "康师傅", "粮食制品", 8),
        Product("矿泉水", "康师傅", "饮料类", 2),
        Product("养乐多", "养乐多", "乳与乳制品", 6),
        Product("农夫山泉饮用水", "农夫山泉", "饮料类", 2),
        Product("维他命水", "农夫山泉", "饮料类", 6),
        Product("黑麦代餐面包", "舌里", "粮食制品", 18),
        Product("德芙丝滑巧克力", "德芙", "巧克力制品", 49),
        Product("士力架", "德芙", "巧克力制品", 28)
    )
    private val stores = listOf(
        Store("恒隆广场", "上海"),
        Store("新光天地", "北京"),
        Store("丽柏广场", "广州"),
        Store("西武百货", "深圳"),
        Store("卓展", "长春"),
        Store("时代广场", "大连"),
        Store("德基", "南京"),
        Store("恒隆广场", "上海"),
        Store("武汉国际广场", "武汉"),
        Store("中大国际", "西安"),
        Store("仁和春天", "成都")
    )

    override suspend fun doWork(): Result = coroutineScope {
        initProduct()
        initStore()
        initStock()
        Result.success()
    }

    private suspend fun initProduct() {
        val dao = inject<ProductDao>(ProductDao::class.java)
        products.forEach { dao.value.insertProduct(it) }
    }

    private suspend fun initStore() {
        val dao = inject<StoreDao>(StoreDao::class.java)
        stores.forEach { dao.value.insertStore(it) }
    }

    private suspend fun initStock() {
        val dao = inject<StockDao>(StockDao::class.java)
        stores.forEach {
            for (i in 0..r.nextInt(products.size)) {
                dao.value.insertStock(Stock(it.name, r.ranChoice(products), "test", r.nextInt(50)))
            }
        }
    }
}

private fun Random.ranChoice(list: List<Product>) = list[this.nextInt(list.size)].name