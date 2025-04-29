package com.yun.yunseoultransportation.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yun.yunseoultransportation.common.model.UiState
import com.yun.yunseoultransportation.common.model.weather.NowWeatherData
import com.yun.yunseoultransportation.common.model.weather.toNowWeatherData
import com.yun.yunseoultransportation.domain.model.busStation.BusStationInfo
import com.yun.yunseoultransportation.domain.model.busStation.BusStationResult
import com.yun.yunseoultransportation.domain.model.weather.NowWeather
import com.yun.yunseoultransportation.domain.model.weather.WeatherResult
import com.yun.yunseoultransportation.domain.usecase.BusUseCase
import com.yun.yunseoultransportation.domain.usecase.WeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val weatherUseCase: WeatherUseCase,
    private val busUseCase: BusUseCase
) : ViewModel() {

private val _nowWeather = MutableStateFlow<UiState<NowWeatherData>>(UiState())
    val nowWeather = _nowWeather.asStateFlow()

    fun getNowWeather(location: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _nowWeather.value = UiState.loading()
            when(val result = weatherUseCase.getNowWeather(location)){
                is WeatherResult.Success<NowWeather> -> {
                    Log.d("yslee", "getNowWeather success > ${result.data}")
                    _nowWeather.value = UiState.success(result.data.toNowWeatherData())
                }
                is WeatherResult.Error -> {
                    Log.e("yslee", "getNowWeather error > ${result.message}")
                    _nowWeather.value = UiState.error(result.message)
                }
            }
        }
    }

    // 노선번호에 해당하는 노선 목록 조회
    // ex) 146 검색 > 146 번호가 들어가는 버스 목록 조회
    fun getBusRouteList(strSrch: String, callback: (List<BusStationInfo>) -> Unit) {
        viewModelScope.launch {
            when (val result = busUseCase.getBusRouteList(strSrch)) {
                is BusStationResult.Success -> callback(result.busStationInfo)
                is BusStationResult.Empty -> {}
                is BusStationResult.Error -> Log.e(
                    "yslee",
                    "getBusRouteList error > ${result.message}"
                )
                is BusStationResult.Loading -> {}
            }
        }
    }
}