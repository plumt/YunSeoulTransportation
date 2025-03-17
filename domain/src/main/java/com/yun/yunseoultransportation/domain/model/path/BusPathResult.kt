package com.yun.yunseoultransportation.domain.model.path

sealed class BusPathResult {
    data class Success(val busPathInfo: List<BusPathInfo>) : BusPathResult()
    data class Error(val message: String) : BusPathResult()
    data object Empty : BusPathResult()
}