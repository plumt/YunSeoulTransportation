package com.yun.yunseoultransportation.ui.map

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yun.yunseoultransportation.common.model.BusDataModel
import com.yun.yunseoultransportation.common.model.toBusDataModel
import com.yun.yunseoultransportation.domain.model.bus.busRouteList.ItemList
import com.yun.yunseoultransportation.domain.model.bus.staionByRoute.StaionByRouteResponse
import com.yun.yunseoultransportation.domain.usecase.BusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val busUseCase: BusUseCase
) : ViewModel() {

    // 번호로 검색된 버스 목록
    private val _busRouteInfoList = MutableLiveData<List<ItemList>>()
    val busRouteInfoList: LiveData<List<ItemList>> get() = _busRouteInfoList

    // 실시간 버스 목록
    private val _busData = MutableLiveData<List<BusDataModel>>()
    val busData: LiveData<List<BusDataModel>> get() = _busData

    // 버스 경로
    private val _busPathData = MutableLiveData<List<BusDataModel>>()
    val busPathData: LiveData<List<BusDataModel>> get() = _busPathData

    // 버스 정류장 목록
    private val _busStationList = MutableLiveData<List<BusDataModel>>()
    val busStationList: LiveData<List<BusDataModel>> get() = _busStationList

    private val _selectedBusRouteId = MutableLiveData<String>()
    val selectedBusRouteId: LiveData<String> get() = _selectedBusRouteId

    // 노선번호에 해당하는 노선 목록 조회
    // ex) 146 검색 > 146 번호가 들어가는 버스 목록 조회
    fun getBusRouteList(strSrch: String) {
        viewModelScope.launch {
            busUseCase.getBusRouteList(strSrch).onSuccess {
                _busRouteInfoList.value = it.msgBody.itemList
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

    // 노선 경로 조회
    // 버스 고유 id를 입력해 해당 버스의 전체 경로 조회
    fun getRoutePath(busRouteId: String) {
        viewModelScope.launch {
            busUseCase.getRoutePath(busRouteId).onSuccess {
                _busPathData.value = it.toBusDataModel()
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

    // 노선ID로 차량들의 위치정보를 조회한다
    // 실시간 위치, 캐싱 1초
    fun getBusPosByRtid(busRouteId: String) {
        viewModelScope.launch {
            _selectedBusRouteId.value = busRouteId
            busUseCase.getBusPosByRtid(busRouteId).onSuccess {
                _busData.value = it.toBusDataModel()
                Log.d("yslee", "getBusPosByRtid : $it")
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

    fun getStaionByRoute(busRouteId: String) {
        viewModelScope.launch {
            busUseCase.getStaionByRoute(busRouteId).onSuccess {
                _busStationList.value = it.toBusDataModel()
                Log.d("yslee","busUseCase > $it")
            }.onFailure {
                it.printStackTrace()
            }
        }
    }
}