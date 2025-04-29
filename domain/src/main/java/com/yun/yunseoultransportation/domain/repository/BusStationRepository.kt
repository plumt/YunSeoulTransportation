package com.yun.yunseoultransportation.domain.repository

import com.yun.yunseoultransportation.domain.model.ApiResult
import com.yun.yunseoultransportation.domain.model.busStation.BusStationDetail
import com.yun.yunseoultransportation.domain.model.busStation.BusStationResult
import com.yun.yunseoultransportation.domain.model.busStation.BusStationRoute

interface BusStationRepository {
    suspend fun getStationByUid(arsId: String): ApiResult<List<BusStationDetail>>
    suspend fun getStationByName(stSrch: String): BusStationResult
    suspend fun getStationByPos(
        tmX: String,
        tmY: String,
        radius: String,
    ): BusStationResult
    suspend fun getRouteByStation(arsId: String): ApiResult<List<BusStationRoute>>
    suspend fun getBustimeByStation(
        arsId: String,
        busRouteId: String,
    ): BusStationResult
}