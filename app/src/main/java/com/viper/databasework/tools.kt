package com.viper.databasework

import android.content.Context
import android.graphics.Rect
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent

/**
 * created by viper on 2021/6/4
 * desc
 */
private var toast: Toast? = null
fun String?.showToast() {
    this ?: return
    CoroutineScope(Dispatchers.Main).launch {
        toast?.cancel()
        toast = Toast.makeText(KoinJavaComponent.get(Context::class.java), this@showToast, Toast.LENGTH_SHORT)
        toast?.show()
    }
}

fun String.log(){
    Log.d("TAG_", this)
}

class VerticalItemDecoration: RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val space = dip2px(10f, view.context)
        val position = parent.getChildAdapterPosition(view)
        val totalCount = parent.adapter?.itemCount ?: return
        if (position == totalCount - 1) {
            outRect.bottom = space
        } else {
            outRect.bottom = 0
        }
        outRect.top = space
    }
}

fun dip2px(value: Float, context: Context): Int {
    val scale = context.resources.displayMetrics.density
    return (value * scale + 0.5).toInt()
}