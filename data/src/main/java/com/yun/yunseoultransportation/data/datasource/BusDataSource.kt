package com.yun.yunseoultransportation.data.datasource

import com.yun.yunseoultransportation.data.model.bus.busPosByRtid.BusPosByRtidResponse
import com.yun.yunseoultransportation.data.model.bus.busPosByVehId.BusPosByVehIdResponse
import com.yun.yunseoultransportation.data.model.bus.busRouteList.BusRouteListResponse
import com.yun.yunseoultransportation.data.model.bus.routePath.RoutePathResponse
import com.yun.yunseoultransportation.data.model.bus.staionByRoute.StaionByRouteResponse
import retrofit2.Response

interface BusDataSource {
    suspend fun getStaionByRoute(busRouteId: String): Response<StaionByRouteResponse>
    suspend fun getBusPosByVehId(vehId: String): Response<BusPosByVehIdResponse>
    suspend fun getBusPosByRtid(
        busRouteId: String,
        startOrd: String,
        endOrd: String
    ): Response<BusPosByRtidResponse>
    suspend fun getBusRouteList(strSrch: String): Response<BusRouteListResponse>
    suspend fun getRoutePath(busRouteId: String): Response<RoutePathResponse>
}