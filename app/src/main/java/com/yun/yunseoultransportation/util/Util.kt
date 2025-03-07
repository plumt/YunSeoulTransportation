package com.yun.yunseoultransportation.util

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue

object Util {

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