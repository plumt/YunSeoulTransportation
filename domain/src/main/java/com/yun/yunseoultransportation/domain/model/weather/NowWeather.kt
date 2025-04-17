package com.yun.yunseoultransportation.domain.model.weather

data class NowWeather(
    val location: String,
    val state: String,
    val temperature: String,
    val url: String,
    val feelTemperature: String,
    val humidity: String,
    val windSpeed: String,
    val dust: Int,
    val uDust: Int,
    val uv: Int,
    val compare: String

//    val hourly: Hourly? = null,
//    val weekly: Weekly? = null,
)