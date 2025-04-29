package com.yun.yunseoultransportation.data.datasource

import com.yun.yunseoultransportation.data.model.busStation.bustimeByStation.BustimeByStationResponse
import com.yun.yunseoultransportation.data.model.busStation.routeByStation.RouteByStationResponse
import com.yun.yunseoultransportation.data.model.busStation.stationByName.StationByNameResponse
import com.yun.yunseoultransportation.data.model.busStation.stationByPos.StationByPosResponse
import com.yun.yunseoultransportation.data.model.busStation.stationByUid.StationByUidResponse
import retrofit2.Response

interface BusStationDataSource {
    suspend fun getStationByUid(arsId: String): Response<StationByUidResponse>
    suspend fun getStationByName(stSrch: String): Response<StationByNameResponse>
    suspend fun getStationByPos(
        tmX: String,
        tmY: String,
        radius: String,
    ): Response<StationByPosResponse>

    suspend fun getRouteByStation(arsId: String): Response<RouteByStationResponse>
    suspend fun getBustimeByStation(
        arsId: String,
        busRouteId: String,
    ): Response<BustimeByStationResponse>
}