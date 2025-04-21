package com.yun.yunseoultransportation

import android.util.Log
import android.view.MenuItem
import androidx.appcompat.view.menu.MenuItemImpl
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.reflect.typeOf

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    // 로딩 프로그레스바 변수
    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _isBottomNav = MutableLiveData<Boolean>(false)
    val isBottomNav: LiveData<Boolean> get() = _isBottomNav

    private var currentTab: Int = R.id.homeFragment         // 현재 선택된 탭 저장
    var onNavigate: ((Int) -> Unit)? = null


    /**
     * 로딩 프로그레스바 노출/숨김
     */
    fun setLoading(isLoading: Boolean) {
        _isLoading.postValue(isLoading)
    }

    fun showBottomNav() {
        _isBottomNav.value = true
    }

    fun hideBottomNav() {
        _isBottomNav.value = false
    }

//    fun bottomNavTabEvent(item: Any): Boolean {
//        val menuItem = item as? MenuItem ?: return false
//        val destinationId = when (menuItem.title) {
//            "home" -> R.id.homeFragment
//            "map" -> R.id.mapFragment
////            "departure" -> '3'
////            "favorite" -> '4'
////            "setting" -> '5'
//            else -> return false
//        }
//        if (currentTab == destinationId) return false
//        currentTab = destinationId
//        onNavigate?.invoke(destinationId)
//        return true
//    }
}