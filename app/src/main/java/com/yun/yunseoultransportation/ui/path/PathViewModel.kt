package com.yun.yunseoultransportation.ui.path

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yun.yunseoultransportation.domain.model.path.locationInfoList.LocationInfoListResponse
import com.yun.yunseoultransportation.domain.model.search.keyworkSearch.Documents
import com.yun.yunseoultransportation.domain.model.search.keyworkSearch.KeywordSearchRequest
import com.yun.yunseoultransportation.domain.usecase.PathUseCase
import com.yun.yunseoultransportation.domain.usecase.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PathViewModel @Inject constructor(
    private val pathUseCase: PathUseCase,
    private val searchUseCase: SearchUseCase
) : ViewModel(){

    private val _searchInfoList = MutableLiveData<List<Documents>>()
    val searchInfoList: LiveData<List<Documents>> get() = _searchInfoList

    fun keywordSearch(keyword: String){
        viewModelScope.launch {
            searchUseCase.keywordSearch(
                KeywordSearchRequest(query = keyword)
            ).onSuccess {
                _searchInfoList.value = it.documents
            }.onFailure { it.printStackTrace() }
        }
    }


    fun getPathInfoByBusNSub(){
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