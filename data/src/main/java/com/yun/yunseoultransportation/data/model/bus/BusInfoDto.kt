package com.yun.yunseoultransportation.data.model.bus

import com.yun.yunseoultransportation.domain.model.bus.BusInfo

data class BusInfoDto(
    val latitude: String,
    val longitude: String,
    val plainNo: String,
    val id: String,
) {
    fun toBusInfo(): BusInfo = BusInfo(latitude, longitude, plainNo, id)
}
