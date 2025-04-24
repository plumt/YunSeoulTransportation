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
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.ads.MobileAds
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kakao.vectormap.KakaoMapSdk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
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

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavi.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            val menuId = when (destination.id) {
                R.id.homeFragment, R.id.busFragment, R.id.subwayFragment -> {
                    mainViewModel.showBottomNav()
                    destination.id
                }

                else -> {
                    mainViewModel.hideBottomNav()
                    null
                }
            }

            menuId?.let {
                binding.bottomNavi.post { binding.bottomNavi.setSelectedItemId(it) }
            }
        }
    }
}


