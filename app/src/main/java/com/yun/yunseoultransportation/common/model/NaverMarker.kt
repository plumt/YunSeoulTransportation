package com.yun.yunseoultransportation.common.model

import android.content.res.Resources
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.overlay.OverlayImage
import com.yun.yunseoultransportation.domain.model.bus.BusInfo
import com.yun.yunseoultransportation.domain.model.busStation.BusStationInfo
import com.yun.yunseoultransportation.util.Util.dpToPx

data class NaverMarker(
    val id: String,
    val latitude: String,
    val longitude: String,
    val title: String = "",
    var tag: String? = null,
    var overlayImage: OverlayImage? = null,
    var width: Int? = null,
    var height: Int? = null,
) {
    fun isEnterSize() = width != null && height != null
    fun isEnterImage() = overlayImage != null
    fun setScaleSize(resources: Resources, width: Int, height: Int) {
        val w = dpToPx(resources, width).toInt()
        val h = dpToPx(resources, height).toInt()
        this.width = w
        this.height = h
    }

    fun latLng() = LatLng(latitude.toDouble(), longitude.toDouble())
}

fun List<BusStationInfo>.toBusStationMarker(): List<NaverMarker> =
    this.map { item ->
        NaverMarker(
            id = item.id,
            latitude = item.latitude,
            longitude = item.longitude,
            title = item.busRouteNm
        )
    }

fun List<BusInfo>.toBusMarker(): List<NaverMarker> =
    this.map { item ->
        NaverMarker(
            id = item.id,
            latitude = item.latitude,
            longitude = item.longitude,
            title = item.plainNo
        )
    }