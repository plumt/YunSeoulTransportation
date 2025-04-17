package com.yun.yunseoultransportation.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yun.yunseoultransportation.common.model.UiState
import com.yun.yunseoultransportation.common.model.weather.NowWeatherData
import com.yun.yunseoultransportation.common.model.weather.toNowWeatherData
import com.yun.yunseoultransportation.domain.model.weather.NowWeather
import com.yun.yunseoultransportation.domain.model.weather.WeatherResult
import com.yun.yunseoultransportation.domain.usecase.WeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val weatherUseCase: WeatherUseCase
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
}