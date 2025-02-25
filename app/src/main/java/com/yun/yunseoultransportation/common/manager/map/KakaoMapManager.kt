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
import com.yun.yunseoultransportation.R
import com.yun.yunseoultransportation.common.model.BusDataModel
import com.yun.yunseoultransportation.util.Util.fromDpToPx

class KakaoMapManager(private val kakaoMap: KakaoMap) {

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

    fun bounces(view: View, data: List<BusDataModel>) {
        val latLng: Array<LatLng> =
            data.map { item -> LatLng.from(item.latitude.toDouble(), item.longitude.toDouble()) }
                .toTypedArray()
        val cameraUpdate = CameraUpdateFactory.fitMapPoints(latLng, 50f.fromDpToPx())
        kakaoMap.moveCamera(cameraUpdate)
    }
}