package com.yun.yunseoultransportation.data.model.bus

import com.yun.yunseoultransportation.domain.model.path.BusPathInfo

data class BusPathInfoDto(
    val latitude: String,
    val longitude: String,
    val id: String,
) {
    fun toBusPathInfo() = BusPathInfo(latitude, longitude, id)
}
