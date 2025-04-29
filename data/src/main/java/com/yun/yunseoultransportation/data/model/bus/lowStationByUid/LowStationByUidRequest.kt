package com.yun.yunseoultransportation.data.model.bus.lowStationByUid

data class LowStationByUidRequest(
    val arsId: String,              // 정류소 번호
    val serviceKey: String,         // 서비스키
    val resultType: String          // 응답유형
)
