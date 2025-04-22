package com.yun.yunseoultransportation.util

import android.graphics.Color
import android.net.Uri
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.yun.yunseoultransportation.R

object ViewUtil {

    @BindingAdapter("setWeatherImages")
    @JvmStatic
    fun ImageView.setWeatherImages(path: String?) {
        if (path == null) return
        try {
            GlideToVectorYou
                .init()
                .with(context)
                .setPlaceHolder(R.color.white, R.color.white)
                .load(Uri.parse(path), this)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @BindingAdapter("app:textColorFromBackground")
    @JvmStatic
    fun TextView.setTextColorFromBackground(backgroundColor: Int) {
        // 배경색에서 RGB 값 추출
        val red = Color.red(backgroundColor)
        val green = Color.green(backgroundColor)
        val blue = Color.blue(backgroundColor)

        // 밝기 계산 (W3C 가중치 공식)
        val brightness = (red * 299 + green * 587 + blue * 114) / 1000

        // 밝기에 따라 어두운 배경이면 흰색, 밝은 배경이면 검은색 텍스트
        val textColor = if (brightness < 128) Color.WHITE else Color.BLACK

        // 텍스트 색상 설정
        this.setTextColor(textColor)
    }
}