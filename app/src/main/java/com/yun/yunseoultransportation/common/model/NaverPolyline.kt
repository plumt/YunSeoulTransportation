package com.yun.yunseoultransportation.common.model

import android.content.res.Resources
import com.naver.maps.geometry.LatLng
import com.yun.yunseoultransportation.domain.model.path.BusPathInfo
import com.yun.yunseoultransportation.util.Util.dpToPx

data class NaverPolyline(
    val latLng: List<LatLng>,
    val width: Int,
    val color: Int,
)

fun List<BusPathInfo>.toNaverPolyline(color: Int, width: Int): NaverPolyline =
    NaverPolyline(
        latLng = this.toLatLngList(),
        color = color,
        width = width
    )

fun List<BusPathInfo>.toLatLngList(): List<LatLng> = this.map { item ->
    LatLng(item.latitude.toDouble(), item.longitude.toDouble())
}
