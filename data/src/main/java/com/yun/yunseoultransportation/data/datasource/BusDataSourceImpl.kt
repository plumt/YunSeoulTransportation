package com.yun.yunseoultransportation.data.datasource

import com.yun.yunseoultransportation.data.model.bus.busPosByRtid.BusPosByRtidResponse
import com.yun.yunseoultransportation.data.model.bus.busPosByVehId.BusPosByVehIdResponse
import com.yun.yunseoultransportation.data.model.bus.busRouteList.BusRouteListResponse
import com.yun.yunseoultransportation.data.model.bus.routePath.RoutePathResponse
import com.yun.yunseoultransportation.data.model.bus.staionByRoute.StaionByRouteResponse
import com.yun.yunseoultransportation.data.remote.api.BusApiService
import retrofit2.Response
import javax.inject.Inject

class BusDataSourceImpl @Inject constructor(
    private val busApiService: BusApiService
) : BusDataSource {

    override suspend fun getStaionByRoute(busRouteId: String): Response<StaionByRouteResponse> =
        busApiService.getStaionByRoute(busRouteId = busRouteId)

    override suspend fun getRoutePath(busRouteId: String): Response<RoutePathResponse> =
        busApiService.getRoutePath(busRouteId = busRouteId)

    override suspend fun getBusPosByVehId(vehId: String): Response<BusPosByVehIdResponse> =
        busApiService.getBusPosByVehId(vehId = vehId)

    override suspend fun getBusPosByRtid(
        busRouteId: String,
        startOrd: String,
        endOrd: String
    ): Response<BusPosByRtidResponse> = busApiService.getBusPosByRtid(
        busRouteId = busRouteId,
        startOrd = startOrd,
        endOrd = endOrd,
    )

    override suspend fun getBusRouteList(strSrch: String): Response<BusRouteListResponse> =
        busApiService.getBusRouteList(strSrch = strSrch)
}