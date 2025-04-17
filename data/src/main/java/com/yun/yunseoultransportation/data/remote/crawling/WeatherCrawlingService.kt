package com.yun.yunseoultransportation.data.remote.crawling

import org.jsoup.Connection
import org.jsoup.Jsoup

object WeatherCrawlingService {
    suspend fun weatherSearch(
        location: String,
        url: String,
    ) = Jsoup.connect("${url}${location}날씨")
}