package com.yun.yunseoultransportation.ui.bus

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yun.yunseoultransportation.common.model.BusDataModel
import com.yun.yunseoultransportation.domain.usecase.BusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BusViewModel @Inject constructor(
    private val busUseCase: BusUseCase
) : ViewModel() {

    private val _busData = MutableLiveData<List<BusDataModel>>()
    val busData: LiveData<List<BusDataModel>> get() = _busData

    private val _busPathData = MutableLiveData<List<BusDataModel>>()
    val busPathData: LiveData<List<BusDataModel>> get() = _busPathData

    // ItemList(distance=3029, time=19,
    // pathList=[PathList(routeId=100100216, routeNm=3217, fid=103000108, fname=화양사거리, fx=127.06665877903272, fy=37.54722861370906, tname=영동대교북단, tx=127.06074277599501, ty=37.53689623251369),
    // PathList(routeId=100100209, routeNm=2412, fid=103000093, fname=성수119안전센터, fx=127.05999640824214, fy=37.53748232701054, tname=뚝도아리수정수센터수도박물관, tx=127.04398681747286, ty=37.54135507952214)])

    // 차량ID로 위치정보를 조회
    fun getBusPosByVehId() {
//        viewModelScope.launch {
//            busUseCase.getBusPosByVehId("100100216").onSuccess {
//                Log.d("yslee", "getBusPosByVehId : $it")
//            }.onFailure {
//                it.printStackTrace()
//            }
//        }
    }

    // 노선ID로 차량들의 위치정보를 조회한다
    fun getBusPosByRtid() {
//        viewModelScope.launch {
//            busUseCase.getBusPosByRtid("100100025").onSuccess {
//                val tempBusData = it.msgBody.itemList.map {
//                    BusDataModel(
//                        latitude = it.gpsY,
//                        longitude = it.gpsX,
//                        title = it.plainNo,
//                        id = it.vehId
//                    )
//                }
//                _busData.value = tempBusData
//                Log.d("yslee", "getBusPosByRtid : $it")
//            }.onFailure {
//                it.printStackTrace()
//            }
//        }
    }

    // 노선번호에 해당하는 노선 목록 조회
    fun getBusRouteList() {
//        viewModelScope.launch {
//            busUseCase.getBusRouteList("146").onSuccess {
//            }.onFailure {
//                it.printStackTrace()
//            }
//        }
    }

    fun getRoutePath() {
//        viewModelScope.launch {
//            busUseCase.getRoutePath("100100025").onSuccess {
//                Log.d("yslee","getRoutePath > ${it}")
//                val tempBusData = it.msgBody.itemList.map {
//                    BusDataModel(
//                        latitude = it.gpsY,
//                        longitude = it.gpsX,
//                        title = it.no,
//                        id = it.no
//                    )
//                }
//                _busPathData.value = tempBusData
//            }.onFailure {
//                it.printStackTrace()
//            }
//        }
    }

}