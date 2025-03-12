package com.yun.yunseoultransportation.common.manager.map

import android.content.res.Resources
import android.graphics.Color
import com.naver.maps.geometry.LatLng
import com.naver.maps.geometry.LatLngBounds
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import com.naver.maps.map.overlay.PolylineOverlay
import com.yun.yunseoultransportation.R
import com.yun.yunseoultransportation.common.model.BusDataModel
import com.yun.yunseoultransportation.util.Util.dpToPx

class NaverMapManager(private val naverMap: NaverMap) {

    private val markers: ArrayList<Marker> = arrayListOf()
    private val polyline = PolylineOverlay()

    fun addMarkers(
        data: List<BusDataModel>,
        isClear: Boolean = true,
        isBounds: Boolean = true,
        overlayImage: OverlayImage? = null,
        resources: Resources? = null,
        size: Int? = null
    ) {
        if (isClear) clearMarkers()
        data.map { Pair(LatLng(it.latitude.toDouble(), it.longitude.toDouble()), it.title) }.map {
            markers.add(Marker().apply {
                position = it.first
                if (overlayImage != null) icon = overlayImage
                if(resources != null && size != null){
                    width = dpToPx(resources, size).toInt()
                    height = dpToPx(resources, size).toInt()
                }
                captionText = it.second
                map = naverMap
                zIndex = 3
            })
        }

        if (isBounds) mapBounds(data)
    }

    fun clearMarkers() {
        markers.map { it.map = null }
    }

    fun mapBounds(data: List<BusDataModel>) {
        val bounds = LatLngBounds.Builder()
        val latLngBounds =
            bounds.include(data.map { LatLng(it.latitude.toDouble(), it.longitude.toDouble()) })
                .build()
        val cameraUpdate = CameraUpdate.fitBounds(latLngBounds)
        naverMap.moveCamera(cameraUpdate)
    }

    fun addPolyline(data: List<BusDataModel>, isClear: Boolean = true, isBounds: Boolean = true) {
        if (isClear) clearPolyline()
        data.map { LatLng(it.latitude.toDouble(), it.longitude.toDouble()) }.run {
            polyline.coords = this
        }
        polyline.apply {
            width = 10
            color = Color.BLUE
            capType = PolylineOverlay.LineCap.Round
            joinType = PolylineOverlay.LineJoin.Round
            zIndex = 2
            map = naverMap
        }
        if (isBounds) mapBounds(data)
    }

    fun clearPolyline() {
        polyline.map = null
    }
}