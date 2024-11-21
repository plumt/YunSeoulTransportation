package com.yun.yunseoultransportation.base

import android.os.SystemClock
import android.view.View
import com.yun.yunseoultransportation.common.constants.ViewConstants.Duration.MIN_CLICK_INTERVAL
import java.util.concurrent.atomic.AtomicLong

fun View.setOnSingleClickListener(
    interval: Long = MIN_CLICK_INTERVAL,
    listener: (View) -> Unit
) {
    val lastClickTime = AtomicLong(0L)
    setOnClickListener { view ->
        val currentClickTime = SystemClock.uptimeMillis()
        val elapsedTime = currentClickTime - lastClickTime.get()
        if (elapsedTime >= interval) {
            lastClickTime.set(currentClickTime)
            listener(view)
        }
    }
}