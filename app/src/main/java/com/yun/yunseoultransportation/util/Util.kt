package com.yun.yunseoultransportation.util

import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.util.TypedValue
import android.view.View
import android.view.Window
import android.view.WindowInsets
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

    fun setStatusBarColor(window: Window, color: Int, lightStatusBar: Boolean = true) {
        window.statusBarColor = color

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val flags = window.decorView.systemUiVisibility
            window.decorView.systemUiVisibility = if (lightStatusBar) {
                // 상태바 아이콘을 어둡게 (흰색 배경에 어울리도록)
                flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                // 상태바 아이콘을 밝게 (어두운 배경에 어울리도록)
                flags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) { // Android 15+
            window.decorView.setOnApplyWindowInsetsListener { view, insets ->
                val statusBarInsets = insets.getInsets(WindowInsets.Type.statusBars())
                view.setBackgroundColor(color)
                view.setPadding(0, statusBarInsets.top, 0, 0)
                insets
            }
        }
    }
}