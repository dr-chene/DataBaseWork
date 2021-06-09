package com.viper.databasework.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*

/**
 * created by viper on 2021/6/8
 * desc
 */
@BindingAdapter("bindTime")
fun bindTime(view: TextView, time: Long){
    view.text = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).format(time)
}