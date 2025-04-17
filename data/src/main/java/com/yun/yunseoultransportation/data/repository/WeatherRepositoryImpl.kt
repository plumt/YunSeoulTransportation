package com.yun.yunseoultransportation.data.repository

import com.yun.yunseoultransportation.data.datasource.WeatherDataSource
import com.yun.yunseoultransportation.data.mapper.WeatherMapper
import com.yun.yunseoultransportation.domain.model.weather.NowWeather
import com.yun.yunseoultransportation.domain.model.weather.WeatherResult
import com.yun.yunseoultransportation.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherDataSource: WeatherDataSource,
) : WeatherRepository {

    override suspend fun getNowWeather(location: String): WeatherResult<NowWeather> {
        return try {
            val response = weatherDataSource.getNowWeather(location)
            val doc = response.data
            val weatherDto = WeatherMapper.extractWeatherData(doc)
            val nowWeather = WeatherMapper.mapToNowWeather(weatherDto, location)
            WeatherResult.Success(nowWeather)
        } catch (e: Exception) {
            e.printStackTrace()
            WeatherResult.Error(e.message ?: "getNowWeather error")
        }
    }
}