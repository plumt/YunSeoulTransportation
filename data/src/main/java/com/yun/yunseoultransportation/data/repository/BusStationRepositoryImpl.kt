package com.yun.yunseoultransportation.data.repository

import com.yun.yunseoultransportation.data.datasource.BusStationDataSource
import com.yun.yunseoultransportation.data.mapper.BusStationMapper.Companion.toBusStationDetailList
import com.yun.yunseoultransportation.data.mapper.BusStationMapper.Companion.toBusStationInfo
import com.yun.yunseoultransportation.data.mapper.BusStationMapper.Companion.toBusStationInfoList
import com.yun.yunseoultransportation.data.mapper.BusStationMapper.Companion.toBusStationRouteList
import com.yun.yunseoultransportation.domain.model.ApiResult
import com.yun.yunseoultransportation.domain.model.busStation.BusStationDetail
import com.yun.yunseoultransportation.domain.model.busStation.BusStationResult
import com.yun.yunseoultransportation.domain.model.busStation.BusStationRoute
import com.yun.yunseoultransportation.domain.repository.BusStationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BusStationRepositoryImpl @Inject constructor(
    private val busStationDataSource: BusStationDataSource,
) : BusStationRepository {

    override suspend fun getRouteByStation(arsId: String): Flow<ApiResult<List<BusStationRoute>>> =
        flow {
            try {
                emit(ApiResult.Loading)
                val response = busStationDataSource.getRouteByStation(arsId)
                if (response.isSuccessful) {
                    response.body()?.msgBody?.itemList?.let { item ->
                        val busStationRoute = item.toBusStationRouteList()
                        emit(ApiResult.Success(busStationRoute))
                    } ?: emit(ApiResult.Empty)
                } else {
                    emit(ApiResult.Error(response.message()))
                }
            } catch (e: Exception) {
                emit(ApiResult.Error(e.message ?: "getRouteByStation error"))
            }
        }

    override suspend fun getStationByName(stSrch: String): Flow<BusStationResult> = flow {
        try {
            emit(BusStationResult.Loading)
            val response = busStationDataSource.getStationByName(stSrch)
            if (response.isSuccessful) {
                response.body()?.msgBody?.itemList?.let { item ->
                    val busStationInfo = item.toBusStationInfoList()
                    emit(BusStationResult.Success(busStationInfo))
                } ?: emit(BusStationResult.Empty)
            } else {
                emit(BusStationResult.Error(response.message()))
            }
        } catch (e: Exception) {
            emit(BusStationResult.Error(e.message ?: "getStationByName error"))
        }
    }

    override suspend fun getStationByUid(arsId: String): Flow<ApiResult<List<BusStationDetail>>> =
        flow {
            try {
                emit(ApiResult.Loading)
                val response = busStationDataSource.getStationByUid(arsId)
                if (response.isSuccessful) {
                    response.body()?.msgBody?.itemList?.let { item ->
                        val busStationDetail = item.toBusStationDetailList()
                        emit(ApiResult.Success(busStationDetail))
                    } ?: emit(ApiResult.Empty)
                } else {
                    emit(ApiResult.Error(response.message()))
                }
            } catch (e: Exception) {
                emit(ApiResult.Error(e.message ?: "getStationByUid error"))
            }
        }

    override suspend fun getBustimeByStation(
        arsId: String,
        busRouteId: String,
    ): Flow<BusStationResult> = flow {
        try {
            emit(BusStationResult.Loading)
            val response = busStationDataSource.getBustimeByStation(arsId, busRouteId)
            if (response.isSuccessful) {
                response.body()?.msgBody?.itemList?.let { item ->
                    val busStationInfo = item.toBusStationInfo()
                    emit(BusStationResult.Success(busStationInfo))
                } ?: emit(BusStationResult.Empty)
            } else {
                emit(BusStationResult.Error(response.message()))
            }
        } catch (e: Exception) {
            emit(BusStationResult.Error(e.message ?: "getBustimeByStation error"))
        }
    }

    override suspend fun getStationByPos(
        tmX: String,
        tmY: String,
        radius: String,
    ): Flow<BusStationResult> = flow {
        try {
            emit(BusStationResult.Loading)
            val response = busStationDataSource.getStationByPos(tmX, tmY, radius)
            if (response.isSuccessful) {
                response.body()?.msgBody?.itemList?.let { item ->
                    val busStationInfo = item.toBusStationInfo()
                    emit(BusStationResult.Success(busStationInfo))
                } ?: emit(BusStationResult.Empty)
            } else {
                emit(BusStationResult.Error(response.message()))
            }
        } catch (e: Exception) {
            emit(BusStationResult.Error(e.message ?: "getStationByPos error"))
        }
    }
}