package com.yun.yunseoultransportation.data.datasource

import com.yun.yunseoultransportation.data.model.weather.WeatherResponse
import com.yun.yunseoultransportation.data.remote.crawling.WeatherCrawlingService
import org.jsoup.nodes.Document
import javax.inject.Inject

class WeatherDataSourceImpl @Inject constructor(
    private val weatherCrawlingService: WeatherCrawlingService,
) : WeatherDataSource {
    override suspend fun getNowWeather(location: String): WeatherResponse =
        WeatherResponse(
            weatherCrawlingService.weatherSearch(
                location = location,
                url = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query="
            ).get()
        )

}