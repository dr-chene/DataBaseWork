package com.viper.databasework

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.WorkManager
import com.viper.databasework.entry.*
import com.viper.databasework.model.BuyDao
import com.viper.databasework.model.ProductDao
import com.viper.databasework.model.StoreDao
import androidx.work.OneTimeWorkRequestBuilder
import com.viper.databasework.model.StockDao

/**
 * created by viper on 2021/6/8
 * desc
 */
@Database(
    entities = [Brand::class, Buy::class, Product::class, Stock::class, Store::class, Vendor::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getProductDao(): ProductDao
    abstract fun getStoreDao(): StoreDao
    abstract fun getBuyDao(): BuyDao
    abstract fun getStockDao(): StockDao

    companion object {
        private const val DATABASE_NAME = "databasework.db"

        // For Singleton instantiation
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .addCallback(
                    object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            val request = OneTimeWorkRequestBuilder<InitDatabaseWorker>().build()
                            WorkManager.getInstance(context).enqueue(request)
                        }
                    }
                ).build()
        }
    }
}