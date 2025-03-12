package com.yun.yunseoultransportation.domain.usecase

import com.yun.yunseoultransportation.domain.model.bus.busPosByRtid.BusPosByRtidResponse
import com.yun.yunseoultransportation.domain.model.bus.busPosByVehId.BusPosByVehIdResponse
import com.yun.yunseoultransportation.domain.model.bus.busRouteList.BusRouteListResponse
import com.yun.yunseoultransportation.domain.model.bus.routePath.RoutePathResponse
import com.yun.yunseoultransportation.domain.model.bus.staionByRoute.StaionByRouteRequest
import com.yun.yunseoultransportation.domain.model.bus.staionByRoute.StaionByRouteResponse
import com.yun.yunseoultransportation.domain.repository.BusRepository
import javax.inject.Inject

class BusUseCase @Inject constructor(
    private val busRepository: BusRepository
) {

    suspend fun getStaionByRoute(busRouteId: String): Result<StaionByRouteResponse> {
        return busRepository.getStaionByRoute(busRouteId)
    }

    suspend fun getRoutePath(busRouteId: String): Result<RoutePathResponse> {
        return busRepository.getRoutePath(busRouteId)
    }

    suspend fun getBusPosByVehId(vehId: String): Result<BusPosByVehIdResponse> {
        return busRepository.getBusPosByVehId(vehId)
    }

    suspend fun getBusPosByRtid(busRouteId: String): Result<BusPosByRtidResponse> {
        return busRepository.getBusPosByRtid(busRouteId)
    }

    suspend fun getBusRouteList(strSrch: String): Result<BusRouteListResponse> {
        return busRepository.getBusRouteList(strSrch)
    }
}