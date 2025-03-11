package com.yun.yunseoultransportation.common.manager.map

import android.graphics.Color
import android.view.View
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.camera.CameraUpdateFactory
import com.kakao.vectormap.label.LabelOptions
import com.kakao.vectormap.label.LabelStyle
import com.kakao.vectormap.label.LabelStyles
import com.kakao.vectormap.label.LabelTextBuilder
import com.kakao.vectormap.label.PolylineLabel
import com.kakao.vectormap.route.RouteLineSegment
import com.kakao.vectormap.route.RouteLineStyle
import com.kakao.vectormap.route.RouteLineStyles
import com.kakao.vectormap.route.RouteLineStylesSet
import com.kakao.vectormap.shape.MapPoints
import com.kakao.vectormap.shape.PolylineOptions
import com.yun.yunseoultransportation.R
import com.yun.yunseoultransportation.common.model.BusDataModel
import com.yun.yunseoultransportation.util.Util.fromDpToPx
import java.util.Arrays

class KakaoMapManager(private val kakaoMap: KakaoMap) {

    fun addPolyline(data: List<BusDataModel>) {

//        val layer = kakaoMap.routeLineManager?.layer
//
//        val busRouteLine = RouteLineStyles.from(RouteLineStyle.from(16f, Color.BLUE))
//        val subwayRouteLine = RouteLineStyles.from(RouteLineStyle.from(16f, Color.RED))
//
//        val stylesSet = RouteLineStylesSet.from(busRouteLine, subwayRouteLine)
//        val segments = Arrays.asList(RouteLineSegment.from(0,stylesSet.getStyles(0)))
//
//        val latLng = data.map { LatLng.from(it.latitude.toDouble(), it.longitude.toDouble()) }
//        PolylineOptions.from(MapPoints.fromLatLng(latLng))
    }

    fun addMarker(data: BusDataModel) {
        val icon = LabelStyle.from(R.drawable.ic_launcher_background).setTextStyles(20, Color.BLACK)
        val latLng = LatLng.from(data.latitude.toDouble(), data.longitude.toDouble())
        val text = LabelTextBuilder().setTexts(data.title)
        val styles = kakaoMap.labelManager?.addLabelStyles(LabelStyles.from(icon))
        val options = LabelOptions.from(LatLng.from(latLng)).setStyles(styles).setTexts(text)
        val layer = kakaoMap.labelManager?.layer
        layer?.addLabel(options)
    }

    fun clearMarker() {
        kakaoMap.labelManager?.removeAllLabelLayer()
    }

    fun bounces(data: List<BusDataModel>) {
        val latLng: Array<LatLng> =
            data.map { item -> LatLng.from(item.latitude.toDouble(), item.longitude.toDouble()) }
                .toTypedArray()
        val cameraUpdate = CameraUpdateFactory.fitMapPoints(latLng, 50f.fromDpToPx())
        kakaoMap.moveCamera(cameraUpdate)
    }
}