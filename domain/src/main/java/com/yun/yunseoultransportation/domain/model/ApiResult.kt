package com.yun.yunseoultransportation.domain.model

sealed class ApiResult<out T> {
    data class Success<T>(val data: T) : ApiResult<T>()
    data class Error(val message: String) : ApiResult<Nothing>()
    data object Empty : ApiResult<Nothing>()
    data object Loading : ApiResult<Nothing>()
}