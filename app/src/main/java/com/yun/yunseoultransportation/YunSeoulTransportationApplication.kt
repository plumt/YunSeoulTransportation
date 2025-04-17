package com.yun.yunseoultransportation

import android.app.Application
import android.util.Log
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class YunSeoulTransportationApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // 애드몹
        MobileAds.initialize(this) { init ->
            Log.d("yslee","Initialization admob complete")
        }
    }
}