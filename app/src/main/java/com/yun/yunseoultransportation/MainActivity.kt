package com.yun.yunseoultransportation

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.yun.yunseoultransportation.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.kakao.vectormap.KakaoMapSdk
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.Base64

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

        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).let {
            navController = it.navController
        }

        KakaoMapSdk.init(this, "9702ab8a9c569a86da0cbfa80a10a8f0")
    }
}


