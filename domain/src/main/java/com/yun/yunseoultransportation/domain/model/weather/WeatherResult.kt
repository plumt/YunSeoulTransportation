package com.yun.yunseoultransportation.domain.model.weather

import com.yun.yunseoultransportation.domain.model.bus.BusResult


sealed class WeatherResult<T> {
    data class Success<T>(val data: T) : WeatherResult<T>()
    data class Error<T>(val message: String) : WeatherResult<T>()
}