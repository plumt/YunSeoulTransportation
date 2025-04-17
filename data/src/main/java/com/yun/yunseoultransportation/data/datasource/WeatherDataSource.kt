package com.yun.yunseoultransportation.data.datasource

import com.yun.yunseoultransportation.data.model.weather.WeatherResponse
import org.jsoup.nodes.Document

interface WeatherDataSource {
    suspend fun getNowWeather(location: String): WeatherResponse
}