package com.yun.yunseoultransportation.domain.usecase

import com.yun.yunseoultransportation.domain.model.weather.NowWeather
import com.yun.yunseoultransportation.domain.model.weather.WeatherResult
import com.yun.yunseoultransportation.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
){

    suspend fun getNowWeather(location: String): WeatherResult<NowWeather> {
        return weatherRepository.getNowWeather(location)
    }
}