package com.yun.yunseoultransportation.domain.model.path.pathInfoByBusNSub

data class PathInfoByBusNSubRequest(
    val startX: String,             // 시작점 X 좌표 (WGS84)
    val startY: String,             // 시작점 Y 좌표 (WGS84)
    val endX: String,               // 도착점 X 좌표 (WGS84)
    val endY: String,               // 도착점 Y 좌표 (WGS84)
    val serviceKey: String,         // 서비스키
    val resultType: String          // 응답유형
)
