package com.yun.yunseoultransportation.data.model.bus.routePath

data class RoutePathRequest(
    val busRouteId: String,         // 노선 시스템 ID
    val serviceKey: String,         // 서비스키
    val resultType: String          // 응답유형
)