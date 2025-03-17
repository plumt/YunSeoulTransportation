package com.yun.yunseoultransportation.common.manager.map

import com.naver.maps.geometry.LatLng
import com.naver.maps.geometry.LatLngBounds
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.PolylineOverlay
import com.yun.yunseoultransportation.common.model.NaverMarker
import com.yun.yunseoultransportation.common.model.NaverPolyline

class NaverMapManager(private val naverMap: NaverMap) {

    private val markers: ArrayList<Marker> = arrayListOf()
    private val polyline = PolylineOverlay()

    fun addMarkers(
        data: List<NaverMarker>,
        clearMarker: String? = null,
        isBounds: Boolean = true,
        zIndex: Int = 3
    ) {
        if (clearMarker != null) clearMarkers(clearMarker)
        data.map { item ->
            markers.add(Marker().apply {
                position = item.latLng()
                if (item.isEnterImage()) icon = item.overlayImage!!
                if (item.isEnterSize()) {
                    width = item.width!!
                    height = item.height!!
                }
                captionText = item.title
                map = naverMap
                this.zIndex = zIndex
                this.tag = item.tag
            })
        }

        if (isBounds) mapBounds(data.map { item ->
            LatLng(
                item.latitude.toDouble(),
                item.longitude.toDouble()
            )
        })
    }

    fun clearMarkers(clearFilter: String) {
        markers.filter { it.tag == clearFilter }.map { it.map = null }
    }

    private fun mapBounds(latLngList: List<LatLng>) {
        val bounds = LatLngBounds.Builder()
        val latLngBounds =
            bounds.include(latLngList).build()
        val cameraUpdate = CameraUpdate.fitBounds(latLngBounds)
        naverMap.moveCamera(cameraUpdate)
    }

    fun addPolyline(data: NaverPolyline, isClear: Boolean = true, isBounds: Boolean = true) {
        if (isClear) clearPolyline()
        polyline.coords = data.latLng
        polyline.apply {
            width = data.width
            color = data.color
            capType = PolylineOverlay.LineCap.Round
            joinType = PolylineOverlay.LineJoin.Round
            zIndex = 2
            map = naverMap
        }
        if (isBounds) mapBounds(data.latLng)
    }

    fun clearPolyline() {
        polyline.map = null
    }
}