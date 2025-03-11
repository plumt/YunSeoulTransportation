package com.yun.yunseoultransportation.common.manager.map

import android.graphics.Color
import com.naver.maps.geometry.LatLng
import com.naver.maps.geometry.LatLngBounds
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.PolylineOverlay
import com.yun.yunseoultransportation.common.model.BusDataModel

class NaverMapManager(private val naverMap: NaverMap) {

    private val markers: ArrayList<Marker> = arrayListOf()
    private val polyline = PolylineOverlay()

    fun addMarkers(data: List<BusDataModel>, isClear: Boolean = true, isBounds: Boolean = true) {
        if (isClear) clearMarkers()
        data.map { LatLng(it.latitude.toDouble(), it.longitude.toDouble()) }.map {
            markers.add(Marker().apply {
                position = it
                map = naverMap
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
        if(isClear) clearPolyline()
        data.map { LatLng(it.latitude.toDouble(), it.longitude.toDouble()) }.run {
            polyline.coords = this
        }
        polyline.apply {
            width = 10
            color = Color.BLUE
            capType = PolylineOverlay.LineCap.Round
            joinType = PolylineOverlay.LineJoin.Round

            map = naverMap
        }
        if(isBounds) mapBounds(data)
    }

    fun clearPolyline() {
        polyline.map = null
    }
}