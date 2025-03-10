package com.yun.yunseoultransportation.base

import android.view.MenuItem
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

object BindingAdapters {

    @BindingAdapter("app:onNavigationItemSelected")
    @JvmStatic
    fun setOnNavigationItemSelectedListener(
        view: BottomNavigationView,
        listener: (View, MenuItem) -> Boolean
    ) {
        view.setOnItemSelectedListener {
            listener(view, it)
            true
        }
    }

    @BindingAdapter("replaceAll")
    fun RecyclerView.replace(list: List<Any>?) {
        list?.let {
            (adapter as? BaseRecyclerViewAdapter.Create<Any, *>)?.run {
                replaceAll(it)
            }
        }
    }
}