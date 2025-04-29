package com.yun.yunseoultransportation.domain.usecase

import com.yun.yunseoultransportation.domain.model.ApiResult
import com.yun.yunseoultransportation.domain.model.busStation.BusStationDetail
import com.yun.yunseoultransportation.domain.model.busStation.BusStationResult
import com.yun.yunseoultransportation.domain.model.busStation.BusStationRoute
import com.yun.yunseoultransportation.domain.repository.BusStationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BusStationUseCase @Inject constructor(
    private val busStationRepository: BusStationRepository,
) {

    /**
     * 고유번호별 정류소 항목 조회
     * 노선 고유번호에 해당하는 정류소 정보를 조회한다.
     */
    suspend fun getStationByUid(arsId: String): Flow<ApiResult<List<BusStationDetail>>> {
        return busStationRepository.getStationByUid(arsId)
    }

    /**
     * 명칭별 정류소 목록 조회
     * 검색어가 포함된 정류소 명칭을 조회한다.
     */
    suspend fun getStationByName(stSrch: String): Flow<BusStationResult> {
        val stationName = stSrch.trim()
        return busStationRepository.getStationByName(stationName)
    }

    /**
     * 좌표 기반 근접 정류소 목록 조회
     * 지정된 좌표와 반경 범위 내의 정류소 목록을 조회한다.
     */
    suspend fun getStationByPos(
        tmX: String,
        tmY: String,
        radius: String,
    ): Flow<BusStationResult> {
        return busStationRepository.getStationByPos(tmX, tmY, radius)
    }

    /**
     * 정류소별 경유 노선 목록 조회
     * 고유번호에 해당하는 경유노선목록을 조회한다.
     */
    suspend fun getRouteByStation(arsId: String): Flow<ApiResult<List<BusStationRoute>>> {
        return busStationRepository.getRouteByStation(arsId)
    }

    /**
     * 정류소별 노선 첫차/막차 예정시간 목록조회
     * 정류소 고유번호와 노선id에 해당하는 첫차/막차 예정시간을 조회한다.
     */
    suspend fun getBustimeByStation(
        arsId: String,
        busRouteId: String,
    ): Flow<BusStationResult> {
        return busStationRepository.getBustimeByStation(arsId, busRouteId)
    }
}