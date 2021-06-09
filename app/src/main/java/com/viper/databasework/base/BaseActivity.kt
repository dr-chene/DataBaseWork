package com.viper.databasework.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * created by viper on 2021/6/4
 * desc
 */
abstract class BaseActivity<T: ViewDataBinding>: AppCompatActivity(), IView {
    protected lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getContentViewResId())
        onInitView()
        onInitAction()
        onSubscribe()
    }
}