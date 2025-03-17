package com.yun.yunseoultransportation.data.model.bus.busPosByVehId

data class BusPosByVehIdRequest(
    val vehId: String,              // 버스ID
    val serviceKey: String,         // 서비스키
    val resultType: String          // 응답유형
)
