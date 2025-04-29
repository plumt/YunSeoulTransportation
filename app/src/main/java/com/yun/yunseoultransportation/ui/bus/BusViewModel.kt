package com.yun.yunseoultransportation.ui.bus

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yun.yunseoultransportation.domain.model.ApiResult
import com.yun.yunseoultransportation.domain.model.bus.BusInfo
import com.yun.yunseoultransportation.domain.model.bus.BusResult
import com.yun.yunseoultransportation.domain.model.busStation.BusStationInfo
import com.yun.yunseoultransportation.domain.model.busStation.BusStationResult
import com.yun.yunseoultransportation.domain.model.path.BusPathInfo
import com.yun.yunseoultransportation.domain.model.path.BusPathResult
import com.yun.yunseoultransportation.domain.usecase.BusStationUseCase
import com.yun.yunseoultransportation.domain.usecase.BusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BusViewModel @Inject constructor(
    private val busUseCase: BusUseCase,
    private val busStationUseCase: BusStationUseCase,
) : ViewModel() {


    // 번호로 검색된 버스 목록
    private val _busRouteInfoList = MutableStateFlow<List<BusStationInfo>>(arrayListOf())
    val busRouteInfoList = _busRouteInfoList.asStateFlow()

    // 실시간 버스 목록
    private val _busData = MutableStateFlow<List<BusInfo>>(arrayListOf())
    val busData = _busData.asStateFlow()
    val isBusDataVisible =
        busData.map { it.isNotEmpty() }.stateIn(viewModelScope, SharingStarted.Eagerly, false)

    // 버스 경로
    private val _busPathData = MutableStateFlow<List<BusPathInfo>>(arrayListOf())
    val busPathData = _busPathData.asStateFlow()

    // 버스 정류장 목록
    private val _busStationList = MutableStateFlow<List<BusStationInfo>>(arrayListOf())
    val busStationList = _busStationList.asStateFlow()

    private val _selectedBusRouteId = MutableStateFlow<String>("")
    val selectedBusRouteId = _selectedBusRouteId.asStateFlow()

    // 노선번호에 해당하는 노선 목록 조회
    // ex) 146 검색 > 146 번호가 들어가는 버스 목록 조회
    fun getBusRouteList(strSrch: String) {
        viewModelScope.launch {
            when (val result = busUseCase.getBusRouteList(strSrch)) {
                is BusStationResult.Success -> _busRouteInfoList.value = result.busStationInfo
                is BusStationResult.Empty -> {}
                is BusStationResult.Error -> Log.e(
                    "yslee",
                    "getBusRouteList error > ${result.message}"
                )

                is BusStationResult.Loading -> {}
            }
        }
    }

    // 노선 경로 조회
    // 버스 고유 id를 입력해 해당 버스의 전체 경로 조회
    fun getRoutePath(busRouteId: String) {
        viewModelScope.launch {
            when (val result = busUseCase.getRoutePath(busRouteId)) {
                is BusPathResult.Success -> _busPathData.value = result.busPathInfo
                is BusPathResult.Empty -> {}
                is BusPathResult.Error -> Log.d("yslee", "getRoutePath error > ${result.message}")
            }
        }
    }

    // 노선ID로 차량들의 위치정보를 조회한다
    // 실시간 위치, 캐싱 1초
    fun getBusPosByRtid(busRouteId: String) {
        viewModelScope.launch {
            _selectedBusRouteId.value = busRouteId
            when (val result = busUseCase.getBusPosByRtid(busRouteId)) {
                is BusResult.Success -> _busData.value = result.busInfo
                is BusResult.Empty -> {}
                is BusResult.Error -> Log.e("yslee", "getBusPosByRtid error > ${result.message}")
            }
        }
    }

    // 버스 정류장
    fun getStaionByRoute(busRouteId: String) {
        viewModelScope.launch {
            when (val result = busUseCase.getStaionByRoute(busRouteId)) {
                is BusStationResult.Success -> _busStationList.value = result.busStationInfo
                is BusStationResult.Empty -> {}
                is BusStationResult.Error -> Log.e(
                    "yslee",
                    "getStaionByRoute error > ${result.message}"
                )

                is BusStationResult.Loading -> {}
            }
        }
    }

    fun getLowStationByUid(arsId: String) {
//        viewModelScope.launch {
//            when(val result = busUseCase.getLowStationByUid(arsId)) {
//                is BusStationResult.Success -> {
//                    Log.d("yslee","getLowStationByUid > ${result.busStationInfo}")
//                }
//                is BusStationResult.Empty -> {}
//                is BusStationResult.Error -> {
//                    Log.d("yslee","getLowStationByUid error > ${result.message}")
//                }
//            }
//        }
    }

    // 명칭별 정류소 목록 조회
    fun getStationByName(stationName: String) {
        viewModelScope.launch {
            busStationUseCase.getStationByName(stationName)
                .collect { result ->
                    when (result) {
                        is BusStationResult.Loading -> {
                            Log.d("yslee", "getStationByName loading...")
                        }

                        is BusStationResult.Success -> {
                            Log.d("yslee", "getStationByName success > ${result.busStationInfo}")
                        }

                        is BusStationResult.Error -> {
                            Log.e("yslee", "getStationByName error > ${result.message}")
                        }

                        is BusStationResult.Empty -> {
                            Log.d("yslee", "getStationByName empty")
                        }
                    }
                }
        }
    }

    // 고유번호별 정류소 항목 조회
    fun getStationByUid() {
        val arsId = "12121"
        viewModelScope.launch {

            busStationUseCase.getStationByUid(arsId).collect { result ->
                when (result) {
                    is ApiResult.Loading -> {
                        Log.d("yslee", "getStationByUid loading")
                    }

                    is ApiResult.Success -> {
                        Log.d("yslee", "getStationByUid success > ${result.data}")
                    }

                    is ApiResult.Error -> {
                        Log.e("yslee", "getStationByUid error > ${result.message}")
                    }

                    is ApiResult.Empty -> {
                        Log.d("yslee", "getStationByUid empty")
                    }
                }
            }
        }
    }


}