package com.viper.databasework

import androidx.databinding.ObservableInt
import com.viper.databasework.adapter.ProductAdapter
import com.viper.databasework.adapter.ProductCartAdapter
import com.viper.databasework.adapter.StoreAdapter
import com.viper.databasework.model.BuyRepository
import com.viper.databasework.model.ProductRepository
import com.viper.databasework.model.StoreRepository
import com.viper.databasework.view.fragment.SortDialogFragment
import com.viper.databasework.viewmodel.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import kotlin.math.sin

/**
 * created by viper on 2021/6/8
 * desc
 */
val module = module {
    single { AppDatabase.getInstance(get()) }
    single { get<AppDatabase>().getProductDao() }
    single { get<AppDatabase>().getStoreDao() }
    single { get<AppDatabase>().getBuyDao() }
    single { get<AppDatabase>().getStockDao() }

    single { ProductRepository(get()) }
    single { StoreRepository(get()) }
    single { BuyRepository(get()) }

    single { ProductAdapter() }
    single { StoreAdapter() }
    single { (amount: ObservableInt) -> ProductCartAdapter(amount) }

    viewModel { ProductViewModel(get()) }
    viewModel { StoreViewModel(get()) }
    viewModel { SortViewModel() }
    viewModel { BuyViewModel(get()) }
    viewModel { StoreProductsViewModel(get()) }

    single { SortDialogFragment() }
}