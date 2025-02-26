package com.yun.yunseoultransportation.domain.model.bus.busRouteList

data class BusRouteListRequest(
    val strSrch: String,            // 검색할 노선번호
    val serviceKey: String,         // 서비스키
    val resultType: String          // 응답유형
)
