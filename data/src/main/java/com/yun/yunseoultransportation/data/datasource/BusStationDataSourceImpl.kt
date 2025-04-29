package com.yun.yunseoultransportation.data.datasource

import com.yun.yunseoultransportation.data.model.busStation.bustimeByStation.BustimeByStationResponse
import com.yun.yunseoultransportation.data.model.busStation.routeByStation.RouteByStationResponse
import com.yun.yunseoultransportation.data.model.busStation.stationByName.StationByNameResponse
import com.yun.yunseoultransportation.data.model.busStation.stationByPos.StationByPosResponse
import com.yun.yunseoultransportation.data.model.busStation.stationByUid.StationByUidResponse
import com.yun.yunseoultransportation.data.remote.api.BusStationApiService
import retrofit2.Response
import javax.inject.Inject

class BusStationDataSourceImpl @Inject constructor(
    private val busStationApiService: BusStationApiService,
) : BusStationDataSource {

    override suspend fun getRouteByStation(arsId: String): Response<RouteByStationResponse> =
        busStationApiService.getRouteByStation(arsId)

    override suspend fun getStationByName(arsId: String): Response<StationByNameResponse> =
        busStationApiService.getStationByName(arsId)

    override suspend fun getStationByUid(arsId: String): Response<StationByUidResponse> =
        busStationApiService.getStationByUid(arsId)

    override suspend fun getBustimeByStation(
        arsId: String,
        busRouteId: String,
    ): Response<BustimeByStationResponse> =
        busStationApiService.getBustimeByStation(arsId, busRouteId)

    override suspend fun getStationByPos(
        tmX: String,
        tmY: String,
        radius: String,
    ): Response<StationByPosResponse> =
        busStationApiService.getStationByPos(tmX, tmY, radius)
}