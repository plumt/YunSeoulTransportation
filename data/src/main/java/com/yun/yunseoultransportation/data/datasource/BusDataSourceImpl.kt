package com.yun.yunseoultransportation.data.datasource

import com.yun.yunseoultransportation.data.remote.api.BusApiService
import com.yun.yunseoultransportation.domain.model.bus.busPosByRtid.BusPosByRtidRequest
import com.yun.yunseoultransportation.domain.model.bus.busPosByRtid.BusPosByRtidResponse
import com.yun.yunseoultransportation.domain.model.bus.busPosByVehId.BusPosByVehIdRequest
import com.yun.yunseoultransportation.domain.model.bus.busPosByVehId.BusPosByVehIdResponse
import javax.inject.Inject

class BusDataSourceImpl @Inject constructor(
    private val busApiService: BusApiService
) : BusDataSource {

    override suspend fun getBusPosByVehId(busPosByVehIdRequest: BusPosByVehIdRequest): BusPosByVehIdResponse {
        return busApiService.getBusPosByVehId(
            vehId = busPosByVehIdRequest.vehId,
            serviceKey = busPosByVehIdRequest.serviceKey,
            resultType = busPosByVehIdRequest.resultType
        )
    }

    override suspend fun getBusPosByRtid(busPosByRtidRequest: BusPosByRtidRequest): BusPosByRtidResponse {
        return busApiService.getBusPosByRtid(
            busRouteId = busPosByRtidRequest.busRouteId,
            startOrd = busPosByRtidRequest.startOrd,
            endOrd = busPosByRtidRequest.endOrd,
            serviceKey = busPosByRtidRequest.serviceKey,
            resultType = busPosByRtidRequest.resultType
        )
    }
}