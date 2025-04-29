package com.yun.yunseoultransportation.domain.model.busStation

data class BusStationInfo(
    val stationNm: String,              // 정류소 명
    val arsId: String,                  // 정류소 번호
    val stationId: String? = null,      // 정류소 고유 ID
    val latitude: String? = null,       // 위도
    val longitude: String? = null,      // 경도
    val distance: String? = null,       // 거리
)