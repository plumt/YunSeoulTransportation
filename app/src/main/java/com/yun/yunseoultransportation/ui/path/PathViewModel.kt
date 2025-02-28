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

    fun clearKeywordSearchDataList() {
        _searchInfoList.value = arrayListOf()
    }


    fun getPathInfoByBusNSub(endX: String, endY: String){
        viewModelScope.launch {
            pathUseCase.getPathInfoByBusNSub(
                startX = "127.0659",
                startY = "37.5474",
                endX = endX,
                endY = endY
            ).onSuccess {
//                ItemList(distance=3029, time=19,
                //                pathList=[
                //                PathList(routeId=100100216, routeNm=3217, fid=103000108, fname=화양사거리, fx=127.06665877903272, fy=37.54722861370906, tname=영동대교북단, tx=127.06074277599501, ty=37.53689623251369),
                //                PathList(routeId=100100209, routeNm=2412, fid=103000093, fname=성수119안전센터, fx=127.05999640824214, fy=37.53748232701054, tname=뚝도아리수정수센터수도박물관, tx=127.04398681747286, ty=37.54135507952214)])
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