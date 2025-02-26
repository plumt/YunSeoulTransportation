package com.yun.yunseoultransportation.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yun.yunseoultransportation.domain.model.search.keyworkSearch.KeywordSearchRequest
import com.yun.yunseoultransportation.domain.usecase.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase
) : ViewModel() {


    fun keywordSearch(keyword: String){
        viewModelScope.launch {
            searchUseCase.keywordSearch(KeywordSearchRequest(query = keyword)
            ).onSuccess {
                Log.d("yslee","keyworkSearch(${keyword}) : $it")
            }.onFailure { it.printStackTrace() }
        }
    }
}