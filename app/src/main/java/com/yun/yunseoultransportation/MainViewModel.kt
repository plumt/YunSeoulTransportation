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


    /**
     * 로딩 프로그레스바 노출/숨김
     */
    fun setLoading(isLoading: Boolean) {
        _isLoading.postValue(isLoading)
    }

    fun bottomNavTabEvent(item: Any): Boolean {
        val menuItem = item as? MenuItem ?: return false

        val destinationId = when (menuItem.title) {
            "home" -> '1'
            "map" -> '2'
            "departure" -> '3'
            "favorite" -> '4'
            "setting" -> '5'
            else -> return false
        }

        Log.d("yslee","destinationId : $destinationId")


//        val destinationId = when (index) {
//            "MAP" -> NavigationConstants.MainScreen.MAP
//            "DIARY" -> NavigationConstants.MainScreen.DIARY
//            "COMMUNITY" -> NavigationConstants.MainScreen.COMMUNITY
//            "SETTING" -> NavigationConstants.MainScreen.SETTING
//            "HOME" -> NavigationConstants.MainScreen.HOME
//            else -> return false
//        }
//        if(destinationId == moveScreen.value || (moveScreen.value == null && destinationId == NavigationConstants.MainScreen.HOME)) bottomNavDoubleTabEvent(true)
//        else moveScreens(destinationId)
        return true
    }
}