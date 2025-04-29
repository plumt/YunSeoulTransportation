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
import javax.inject.Inject

class BusStationRepositoryImpl @Inject constructor(
    private val busStationDataSource: BusStationDataSource,
) : BusStationRepository {

    override suspend fun getRouteByStation(arsId: String): ApiResult<List<BusStationRoute>> {
        ApiResult.Loading
        return try {
            val response = busStationDataSource.getRouteByStation(arsId)
            if (response.isSuccessful) {
                response.body()?.msgBody?.itemList?.let { item ->
                    val busStationRoute = item.toBusStationRouteList()
                    ApiResult.Success(busStationRoute)
                } ?: ApiResult.Empty
            } else {
                ApiResult.Error(response.message())
            }
        } catch (e: Exception) {
            ApiResult.Error(e.message ?: "getRouteByStation error")
        }
    }

    override suspend fun getStationByName(stSrch: String): BusStationResult {
        BusStationResult.Loading
        return try {
            val response = busStationDataSource.getStationByName(stSrch)
            if (response.isSuccessful) {
                response.body()?.msgBody?.itemList?.let { item ->
                    val busStationInfo = item.toBusStationInfoList()
                    BusStationResult.Success(busStationInfo)
                } ?: BusStationResult.Empty
            } else {
                BusStationResult.Error(response.message())
            }
        } catch (e: Exception) {
            BusStationResult.Error(e.message ?: "getStationByName error")
        }
    }

    override suspend fun getStationByUid(arsId: String): ApiResult<List<BusStationDetail>> {
        ApiResult.Loading
        return try {
            val response = busStationDataSource.getStationByUid(arsId)
            if (response.isSuccessful) {
                response.body()?.msgBody?.itemList?.let { item ->
                    val busStationDetail = item.toBusStationDetailList()
                    ApiResult.Success(busStationDetail)
                } ?: ApiResult.Empty
            } else {
                ApiResult.Error(response.message())
            }
        } catch (e: Exception) {
            ApiResult.Error(e.message ?: "getStationByUid error")
        }
    }

    override suspend fun getBustimeByStation(arsId: String, busRouteId: String): BusStationResult {
        BusStationResult.Loading
        return try {
            val response = busStationDataSource.getBustimeByStation(arsId, busRouteId)
            if (response.isSuccessful) {
                response.body()?.msgBody?.itemList?.let { item ->
                    val busStationInfo = item.toBusStationInfo()
                    BusStationResult.Success(busStationInfo)
                } ?: BusStationResult.Empty
            } else {
                BusStationResult.Error(response.message())
            }

        } catch (e: Exception) {
            BusStationResult.Error(e.message ?: "getBustimeByStation error")
        }
    }

    override suspend fun getStationByPos(
        tmX: String,
        tmY: String,
        radius: String,
    ): BusStationResult {
        BusStationResult.Loading
        return try {
            val response = busStationDataSource.getStationByPos(tmX, tmY, radius)
            if (response.isSuccessful) {
                response.body()?.msgBody?.itemList?.let { item ->
                    val busStationInfo = item.toBusStationInfo()
                    BusStationResult.Success(busStationInfo)
                } ?: BusStationResult.Empty
            } else {
                BusStationResult.Error(response.message())
            }
        } catch (e: Exception) {
            BusStationResult.Error(e.message ?: "getStationByPos error")
        }
    }
}