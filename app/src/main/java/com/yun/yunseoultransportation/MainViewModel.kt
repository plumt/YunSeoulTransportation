package com.yun.yunseoultransportation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    // 로딩 프로그레스바 변수
    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> get() = _isLoading


    /**
     * 로딩 프로그레스바 노출/숨김
     */
    fun setLoading(isLoading: Boolean) {
        _isLoading.postValue(isLoading)
    }
}