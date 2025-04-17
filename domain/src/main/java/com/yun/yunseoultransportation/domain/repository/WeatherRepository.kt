package com.yun.yunseoultransportation.domain.repository

import com.yun.yunseoultransportation.domain.model.weather.NowWeather
import com.yun.yunseoultransportation.domain.model.weather.WeatherResult

interface WeatherRepository {
    suspend fun getNowWeather(location: String): WeatherResult<NowWeather>
}