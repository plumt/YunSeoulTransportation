package com.yun.yunseoultransportation

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.yun.yunseoultransportation.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment

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

    }
}