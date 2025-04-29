package com.yun.yunseoultransportation.domain.repository

import com.yun.yunseoultransportation.domain.model.ApiResult
import com.yun.yunseoultransportation.domain.model.busStation.BusStationDetail
import com.yun.yunseoultransportation.domain.model.busStation.BusStationResult
import com.yun.yunseoultransportation.domain.model.busStation.BusStationRoute
import kotlinx.coroutines.flow.Flow

interface BusStationRepository {
    suspend fun getStationByUid(arsId: String): Flow<ApiResult<List<BusStationDetail>>>
    suspend fun getStationByName(stSrch: String): Flow<BusStationResult>
    suspend fun getStationByPos(
        tmX: String,
        tmY: String,
        radius: String,
    ): Flow<BusStationResult>
    suspend fun getRouteByStation(arsId: String): Flow<ApiResult<List<BusStationRoute>>>
    suspend fun getBustimeByStation(
        arsId: String,
        busRouteId: String,
    ): Flow<BusStationResult>
}