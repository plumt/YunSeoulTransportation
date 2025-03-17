package com.yun.yunseoultransportation.domain.repository

import com.yun.yunseoultransportation.domain.model.bus.BusResult
import com.yun.yunseoultransportation.domain.model.busStation.BusStationResult
import com.yun.yunseoultransportation.domain.model.path.BusPathResult

interface BusRepository {
    suspend fun getStaionByRoute(busRouteId: String): BusStationResult
    suspend fun getBusPosByVehId(vehId: String): BusResult
    suspend fun getBusPosByRtid(busRouteId: String): BusResult
    suspend fun getBusRouteList(strSrch: String): BusStationResult
    suspend fun getRoutePath(busRouteId: String): BusPathResult
}