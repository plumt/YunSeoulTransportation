package com.yun.yunseoultransportation.domain.repository

import com.yun.yunseoultransportation.domain.model.bus.busPosByRtid.BusPosByRtidResponse
import com.yun.yunseoultransportation.domain.model.bus.busPosByVehId.BusPosByVehIdResponse
import com.yun.yunseoultransportation.domain.model.bus.busRouteList.BusRouteListResponse
import com.yun.yunseoultransportation.domain.model.bus.routePath.RoutePathResponse
import com.yun.yunseoultransportation.domain.model.bus.staionByRoute.StaionByRouteResponse

interface BusRepository {
    suspend fun getStaionByRoute(busRouteId: String): Result<StaionByRouteResponse>
    suspend fun getBusPosByVehId(vehId: String): Result<BusPosByVehIdResponse>
    suspend fun getBusPosByRtid(busRouteId: String): Result<BusPosByRtidResponse>
    suspend fun getBusRouteList(strSrch: String): Result<BusRouteListResponse>
    suspend fun getRoutePath(busRouteId: String): Result<RoutePathResponse>
}