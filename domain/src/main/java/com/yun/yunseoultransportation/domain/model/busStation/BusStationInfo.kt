package com.yun.yunseoultransportation.domain.model.busStation

data class BusStationInfo(
    val id: String,
    val busRouteNm: String,
    val latitude: String,
    val longitude: String,
    val stationNm: String,
    val edationNm: String,
    val busRouteId: String
)
