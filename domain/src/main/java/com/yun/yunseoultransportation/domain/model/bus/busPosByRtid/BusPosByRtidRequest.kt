package com.yun.yunseoultransportation.domain.model.bus.busPosByRtid

data class BusPosByRtidRequest(
    val busRouteId: String,         // 노선ID
    val startOrd: String,           // 시작 정륲소 순번
    val endOrd: String,             // 종료 정류소 순번
    val serviceKey: String,         // 서비스키
    val resultType: String          // 응답유형
)
