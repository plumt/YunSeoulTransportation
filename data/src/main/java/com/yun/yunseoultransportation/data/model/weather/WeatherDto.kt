package com.yun.yunseoultransportation.data.model.weather

import com.yun.yunseoultransportation.domain.model.weather.NowWeather

data class WeatherDto(
    var location: String? = null,               // 날씨 주소
    val state: String? = null,                  // 날씨 상태(맑음, 흐림 등)
    val temperature: String? = null,            // 현재 온도
    val url: String? = null,                    // 날씨 이미지
//    val weatherDetail: String? = null,
    val feelTemperature: String? = null,        // 체감온도
    val humidity: String? = null,               // 습도
    val windSpeed: String? = null,              // 풍속
    val dust: Int? = null,                      // 미세먼지
    val uDust: Int? = null,                     // 초미세먼지
    val uv: Int? = null,                        // 자외선
    val compare: String? = null,                // 어제 비교

//    val hourly: Hourly? = null,
//    val weekly: Weekly? = null,

) {
    fun toNowWeather(): NowWeather = NowWeather(
        location!!,
        state!!,
        temperature!!,
        url!!,
        feelTemperature!!,
        humidity!!,
        windSpeed!!,
        dust!!,
        uDust!!,
        uv!!,
        compare!!
    )
}

data class Hourly(
    val time: String,
    val num: String,
    val wea: String,
    val url: String,
    val info: String = "",
)

data class Weekly(
    val dayOfWeek: String,
    val date: String,
    val amPercent: String,
    val pmPercent: String,
    val lowTemp: String,
    val highTemp: String,
    val amImg: String,
    val pmImg: String,
)