package com.viper.databasework.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * created by viper on 2021/6/4
 * desc
 */
abstract class BaseFragment<T: ViewDataBinding>: Fragment(), IView {
    protected lateinit var binding: T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getContentViewResId(), container, false)
        context ?: return binding.root

        onInitView()
        onInitAction()
        onSubscribe()

        return binding.root
    }
}