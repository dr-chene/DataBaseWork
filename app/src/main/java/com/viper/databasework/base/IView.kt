package com.viper.databasework.base

/**
 * created by viper on 2021/6/4
 * desc
 */
interface IView {
    fun onInitView()

    fun onInitAction()

    fun onSubscribe(){}

    fun getContentViewResId(): Int
}