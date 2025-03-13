package com.yun.yunseoultransportation.util

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

object Util {

    fun <T> Flow<T>.observeWithLifecycle(
        lifecycleOwner: LifecycleOwner,
        lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
        action: suspend (T) -> Unit
    ) {
        lifecycleOwner.lifecycleScope.launch {
            lifecycleOwner.repeatOnLifecycle(lifecycleState) {
                collect { action(it) }
            }
        }
    }

    fun Float.fromDpToPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

    /**
     * 네비바 높이바
     */
    fun getNavigationBarHeight(context: Context): Int {
        val resourceId =
            context.resources.getIdentifier("navigation_bar_height", "dimen", "android")
        return if (resourceId > 0) {
            context.resources.getDimensionPixelSize(resourceId)
        } else {
            0
        }
    }
    /**
     * dp를 px로 변경
     *
     * @param resources
     * @param nValue
     * @return
     */
    @JvmStatic
    fun dpToPx(resources: Resources, nValue: Int): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            nValue.toFloat(),
            resources.displayMetrics
        )
    }
}