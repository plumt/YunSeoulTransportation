package com.yun.yunseoultransportation.data.datasource

import com.yun.yunseoultransportation.data.remote.api.BusApiService
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
import javax.inject.Inject

class BusDataSourceImpl @Inject constructor(
    private val busApiService: BusApiService
) : BusDataSource {

    override suspend fun getStaionByRoute(staionByRouteRequest: StaionByRouteRequest): StaionByRouteResponse {
        return busApiService.getStaionByRoute(
            busRouteId = staionByRouteRequest.busRouteId,
            serviceKey = staionByRouteRequest.serviceKey,
            resultType = staionByRouteRequest.resultType
        )
    }

    override suspend fun getRoutePath(routePathRequest: RoutePathRequest): RoutePathResponse {
        return busApiService.getRoutePath(
            busRouteId = routePathRequest.busRouteId,
            serviceKey = routePathRequest.serviceKey,
            resultType = routePathRequest.resultType
        )
    }

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

    override suspend fun getBusRouteList(busRouteListRequest: BusRouteListRequest): BusRouteListResponse {
        return busApiService.getBusRouteList(
            strSrch = busRouteListRequest.strSrch,
            serviceKey = busRouteListRequest.serviceKey,
            resultType = busRouteListRequest.resultType
        )
    }
}