package com.yun.yunseoultransportation.domain.model.bus

sealed class BusResult {
    data class Success(val busInfo: List<BusInfo>) : BusResult()
    data class Error(val message: String) : BusResult()
    data object Empty : BusResult()
}