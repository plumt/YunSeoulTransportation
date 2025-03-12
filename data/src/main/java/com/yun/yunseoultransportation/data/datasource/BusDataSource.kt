package com.yun.yunseoultransportation.data.datasource

import com.yun.yunseoultransportation.domain.model.bus.busPosByRtid.BusPosByRtidRequest
import com.yun.yunseoultransportation.domain.model.bus.busPosByRtid.BusPosByRtidResponse
import com.yun.yunseoultransportation.domain.model.bus.busPosByVehId.BusPosByVehIdRequest
import com.yun.yunseoultransportation.domain.model.bus.busPosByVehId.BusPosByVehIdResponse
import com.yun.yunseoultransportation.domain.model.bus.busRouteList.BusRouteListRequest
import com.yun.yunseoultransportation.domain.model.bus.busRouteList.BusRouteListResponse
import com.yun.yunseoultransportation.domain.model.bus.routePath.RoutePathRequest
import com.yun.yunseoultransportation.domain.model.bus.routePath.RoutePathResponse
import com.yun.yunseoultransportation.domain.model.bus.staionByRoute.StaionByRouteRequest
import com.yun.yunseoultransportation.domain.model.bus.staionByRoute.StaionByRouteResponse

interface BusDataSource {
    suspend fun getStaionByRoute(staionByRouteRequest: StaionByRouteRequest): StaionByRouteResponse
    suspend fun getBusPosByVehId(busPosByVehIdRequest: BusPosByVehIdRequest): BusPosByVehIdResponse
    suspend fun getBusPosByRtid(busPosByRtidRequest: BusPosByRtidRequest): BusPosByRtidResponse
    suspend fun getBusRouteList(busRouteListRequest: BusRouteListRequest): BusRouteListResponse
    suspend fun getRoutePath(routePathRequest: RoutePathRequest): RoutePathResponse
}