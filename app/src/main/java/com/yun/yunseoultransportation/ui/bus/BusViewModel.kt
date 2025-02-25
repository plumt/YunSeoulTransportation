package com.yun.yunseoultransportation.ui.bus

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yun.yunseoultransportation.domain.usecase.BusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BusViewModel @Inject constructor(
    private val busUseCase: BusUseCase
) : ViewModel(){

    fun getBusPosByVehId() {
        viewModelScope.launch {
            busUseCase.getBusPosByVehId("111033115").onSuccess {
                Log.d("yslee","getBusPosByVehId : $it")
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

    fun getBusPosByRtid() {
        viewModelScope.launch {
            busUseCase.getBusPosByRtid("100100118").onSuccess {
                Log.d("yslee","getBusPosByRtid : $it")
            }.onFailure {
                it.printStackTrace()
            }
        }
    }
}