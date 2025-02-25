package com.yun.yunseoultransportation.util

import android.content.res.Resources

object Util {

    fun Float.fromDpToPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()
}