package com.yun.yunseoultransportation.data.repository

import com.yun.yunseoultransportation.data.datasource.BusDataSource
import com.yun.yunseoultransportation.data.mapper.BusMapper.Companion.toBusInfoList
import com.yun.yunseoultransportation.data.mapper.BusMapper.Companion.toBusPathInfoList
import com.yun.yunseoultransportation.data.mapper.BusMapper.Companion.toBusStationInfoList
import com.yun.yunseoultransportation.domain.model.bus.BusResult
import com.yun.yunseoultransportation.domain.model.busStation.BusStationResult
import com.yun.yunseoultransportation.domain.model.path.BusPathResult
import com.yun.yunseoultransportation.domain.repository.BusRepository
import javax.inject.Inject

class BusRepositoryImpl @Inject constructor(
    private val busDataSource: BusDataSource,
) : BusRepository {

    override suspend fun getStaionByRoute(busRouteId: String): BusStationResult {
        return try {
            val response = busDataSource.getStaionByRoute(busRouteId)
            if (response.isSuccessful) {
                response.body()?.let { responseBody ->
                    val busStationInfo = responseBody.msgBody.itemList.toBusStationInfoList()
                    BusStationResult.Success(busStationInfo)
                } ?: BusStationResult.Empty
            } else {
                BusStationResult.Error(response.message())
            }
        } catch (e: Exception) {
            BusStationResult.Error(e.message ?: "getStaionByRoute error")
        }
    }


    override suspend fun getRoutePath(busRouteId: String): BusPathResult {
        return try {
            val response = busDataSource.getRoutePath(busRouteId)
            if (response.isSuccessful) {
                response.body()?.let { responseBody ->
                    val busPathInfo = responseBody.msgBody.itemList.toBusPathInfoList()
                    BusPathResult.Success(busPathInfo)
                } ?: BusPathResult.Empty
            } else {
                BusPathResult.Error(response.message())
            }
        } catch (e: Exception) {
            BusPathResult.Error(e.message ?: "getRoutePath error")
        }
    }

    override suspend fun getBusPosByVehId(vehId: String): BusResult {
        return try {
            val response = busDataSource.getBusPosByVehId(vehId)
            if (response.isSuccessful) {
                response.body()?.let { responseBody ->
                    val busInfo = responseBody.msgBody.itemList.toBusInfoList()
                    BusResult.Success(busInfo)
                } ?: BusResult.Empty
            } else {
                BusResult.Error(response.message())
            }
        } catch (e: Exception) {
            BusResult.Error(e.message ?: "getBusPosByVehId error")
        }
    }

    override suspend fun getBusPosByRtid(busRouteId: String): BusResult {
        return try {
            val response = busDataSource.getBusPosByRtid(
                busRouteId = busRouteId,
                startOrd = "1",
                endOrd = "15"
            )
            if (response.isSuccessful) {
                response.body()?.let { responseBody ->
                    val busInfo = responseBody.msgBody.itemList.toBusInfoList()
                    BusResult.Success(busInfo)
                } ?: BusResult.Empty

            } else {
                BusResult.Error(response.message())
            }
        } catch (e: Exception) {
            BusResult.Error(e.message ?: "getBusPosByRtid error")
        }
    }

    override suspend fun getBusRouteList(strSrch: String): BusStationResult {
        return try {
            val response = busDataSource.getBusRouteList(strSrch)
            if (response.isSuccessful) {
                response.body()?.let { responseBody ->
                    val busStationInfo = responseBody.msgBody.itemList.toBusStationInfoList()
                    BusStationResult.Success(busStationInfo)
                } ?: BusStationResult.Empty
            } else {
                BusStationResult.Error(response.message())
            }
        } catch (e: Exception) {
            BusStationResult.Error(e.message ?: "getBusRouteList error")
        }
    }
}