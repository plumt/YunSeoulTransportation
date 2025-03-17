package com.yun.yunseoultransportation.data.model.bus.staionByRoute

data class StaionByRouteRequest(
    val busRouteId: String,         // 노선ID
    val serviceKey: String,         // 서비스키
    val resultType: String          // 응답유형
)
