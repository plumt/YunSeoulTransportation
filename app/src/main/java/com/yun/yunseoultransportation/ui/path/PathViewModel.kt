package com.yun.yunseoultransportation.ui.path

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yun.yunseoultransportation.domain.usecase.PathUseCase
import com.yun.yunseoultransportation.domain.usecase.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PathViewModel @Inject constructor(
    private val pathUseCase: PathUseCase,
    private val searchUseCase: SearchUseCase
) : ViewModel() {

    // 검색 결과 데이터 리스트
    private val _searchInfoList = MutableLiveData<List<com.yun.yunseoultransportation.data.model.search.keyworkSearch.Documents>>()
    val searchInfoList: LiveData<List<com.yun.yunseoultransportation.data.model.search.keyworkSearch.Documents>> get() = _searchInfoList

    // 선택된 검색 결과 아이템
    private val _selectedDocument = MutableLiveData<com.yun.yunseoultransportation.data.model.search.keyworkSearch.Documents?>()
    val selectedDocument: LiveData<com.yun.yunseoultransportation.data.model.search.keyworkSearch.Documents?> get() = _selectedDocument

    // 바텀시트 노출 유무
    private val _isBottomSheetVisible = MutableLiveData<Boolean>(false)
    val isBottomSheetVisible: LiveData<Boolean> get() = _isBottomSheetVisible

    // 키워드 검색 api
//    fun keywordSearch(keyword: String) {
//        viewModelScope.launch {
//            searchUseCase.keywordSearch(
//                KeywordSearchRequest(query = keyword)
//            ).onSuccess {
//                _searchInfoList.value = it.documents
//            }.onFailure { it.printStackTrace() }
//        }
//    }
//
//    // 검색 아이템 선택
//    fun selectDocument(documents: com.yun.yunseoultransportation.data.model.search.keyworkSearch.Documents) {
//        _selectedDocument.value = documents
//        _isBottomSheetVisible.value = true
//    }
//
//    // 검색 아이템 초기화
//    fun clearDocument() {
//        _selectedDocument.value = null
//        _isBottomSheetVisible.value = false
//    }
//
//    // 길찾기 api
//    // startX, Y는 현재 위치 데이터를 받아와야 한다
//    fun getPathInfoByBusNSub(endX: String, endY: String) {
//        viewModelScope.launch {
//            pathUseCase.getPathInfoByBusNSub(
//                startX = "127.0659",
//                startY = "37.5474",
//                endX = endX,
//                endY = endY
//            ).onSuccess {
////                ItemList(distance=3029, time=19,
//                //                pathList=[
//                //                PathList(routeId=100100216, routeNm=3217, fid=103000108, fname=화양사거리, fx=127.06665877903272, fy=37.54722861370906, tname=영동대교북단, tx=127.06074277599501, ty=37.53689623251369),
//                //                PathList(routeId=100100209, routeNm=2412, fid=103000093, fname=성수119안전센터, fx=127.05999640824214, fy=37.53748232701054, tname=뚝도아리수정수센터수도박물관, tx=127.04398681747286, ty=37.54135507952214)])
//                it.msgBody.itemList.forEach {
//                    Log.d("yslee", "getPathInfoByBusNSub : $it")
//                }
//            }.onFailure {
//                it.printStackTrace()
//            }
//        }
//    }
//
//    // 대중교통 검색 api
//    fun getLocationInfoList(stSrch: String) {
//        viewModelScope.launch {
//            pathUseCase.getLocationInfoList(stSrch).onSuccess {
//                Log.d("yslee", "getLocationInfoList : $it")
//            }.onFailure {
//                it.printStackTrace()
//            }
//        }
//
//    }
}