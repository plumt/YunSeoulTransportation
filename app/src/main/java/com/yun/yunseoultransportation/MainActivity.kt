package com.yun.yunseoultransportation

import android.net.wifi.aware.AttachCallback
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.yun.yunseoultransportation.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import com.google.android.gms.ads.MobileAds
import com.kakao.vectormap.KakaoMapSdk
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.run {
            lifecycleOwner = this@MainActivity
            main = mainViewModel
        }

        (supportFragmentManager.findFragmentById(binding.navHostFragment.id) as NavHostFragment).let {
            navController = it.navController
        }


        KakaoMapSdk.init(this, "9702ab8a9c569a86da0cbfa80a10a8f0")

        binding.bottomNavi.setOnItemSelectedListener { item ->
            val destinationId = when (item.itemId) {
                R.id.menu_home -> R.id.homeFragment
                R.id.menu_bus -> R.id.busFragment
                R.id.menu_subway -> R.id.subwayFragment
                else -> return@setOnItemSelectedListener false
            }

            // 현재 화면이면 이동하지 않음
            if (navController.currentDestination?.id == destinationId) {
                return@setOnItemSelectedListener false
            }
            navController.navigate(destinationId)

            true
        }

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.homeFragment, R.id.busFragment, R.id.subwayFragment -> {
                    mainViewModel.showBottomNav()
                }

                else -> mainViewModel.hideBottomNav()
            }
        }
    }
}


