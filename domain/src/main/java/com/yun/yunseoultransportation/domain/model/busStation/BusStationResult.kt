package com.yun.yunseoultransportation.domain.model.busStation

sealed class BusStationResult {
    data class Success(val busStationInfo: List<BusStationInfo>) : BusStationResult()
    data class Error(val message: String) : BusStationResult()
    data object Empty : BusStationResult()
}