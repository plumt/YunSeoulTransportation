package com.yun.yunseoultransportation.ui.path

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yun.yunseoultransportation.domain.usecase.PathUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PathViewModel @Inject constructor(
    private val pathUseCase: PathUseCase
) : ViewModel(){
    fun getPathInfoByBusNSub(){
        getLocationInfoList("화곡")
        return
        viewModelScope.launch {
            pathUseCase.getPathInfoByBusNSub(
                startX = "127.0659",
                startY = "37.5474",
                endX = "127.0360",
                endY = "37.5607"
            ).onSuccess {
//                Log.d("yslee","getPathInfoByBusNSub : $it")
                it.msgBody.itemList.forEach {
                    Log.d("yslee","getPathInfoByBusNSub : $it")
                }
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

    fun getLocationInfoList(stSrch: String){
        viewModelScope.launch {
            pathUseCase.getLocationInfoList(stSrch).onSuccess {
                Log.d("yslee","getLocationInfoList : $it")
            }.onFailure {
                it.printStackTrace()
            }
        }

    }
}