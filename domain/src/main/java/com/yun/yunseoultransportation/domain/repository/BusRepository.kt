package com.yun.yunseoultransportation.domain.repository

import com.yun.yunseoultransportation.domain.model.bus.busPosByVehId.BusPosByVehIdResponse

interface BusRepository {
    suspend fun getBusPosByVehId(vehId: String): Result<BusPosByVehIdResponse>
}