package com.yun.yunseoultransportation.data.datasource

import com.yun.yunseoultransportation.domain.model.bus.busPosByVehId.BusPosByVehIdRequest
import com.yun.yunseoultransportation.domain.model.bus.busPosByVehId.BusPosByVehIdResponse

interface BusDataSource {
    suspend fun getBusPosByVehId(busPosByVehIdRequest: BusPosByVehIdRequest): BusPosByVehIdResponse
}