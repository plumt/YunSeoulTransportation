package com.yun.yunseoultransportation.ui.components

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions
import com.google.android.gms.ads.nativead.NativeAdView
import com.yun.yunseoultransportation.databinding.NativeAdLayoutBinding
import com.yun.yunseoultransportation.databinding.ViewLargeNativeAdBinding

class LargeNativeAdView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private lateinit var binding: ViewLargeNativeAdBinding
    private lateinit var adBinding: NativeAdLayoutBinding
    private var nativeAd: NativeAd? = null

    init {
        initView()
    }

    private fun initView() {
        binding = ViewLargeNativeAdBinding.inflate(LayoutInflater.from(context), this, true)
        loadNativeAd()
    }

    private fun loadNativeAd() {
        val adLoader = AdLoader.Builder(context, "ca-app-pub-3940256099942544/2247696110")
            .forNativeAd { ad: NativeAd ->

                // 이전 광고 해제
                nativeAd?.destroy()
                nativeAd = ad

                // 레이아웃 인플레이션
//                val adView = layoutInflater.inflate(R.layout.native_ad_layout, null) as NativeAdView

                // 광고 요소 매핑
                displayNativeAd(ad)

                // 기존 광고 컨테이너 비우고 새 광고 추가
//                val adFrame = binding.adFrame
//                adFrame.removeAllViews()
//                adFrame.addView(adView)
            }
            .withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.e("yslee", "Ad failed to load: ${adError.message}, code: ${adError.code}")

                }

                override fun onAdLoaded() {

                }
            })
            .withNativeAdOptions(
                NativeAdOptions.Builder()
                    // Methods in the NativeAdOptions.Builder class can be
                    // used here to specify individual options settings.
                    .build()
            )
            .build()
        adLoader.loadAd(AdRequest.Builder().build())

    }

    private fun displayNativeAd(nativeAd: NativeAd) {
        // 데이터 바인딩 레이아웃 인플레이션
        adBinding = NativeAdLayoutBinding.inflate(LayoutInflater.from(context))

        // NativeAd 객체 바인딩
        adBinding.nativeAd = nativeAd

        // 이미지와 같은 추가 요소는 직접 설정 필요
        nativeAd.icon?.let {
            adBinding.adIcon.setImageDrawable(it.drawable)
        }

        nativeAd.images?.let {
            if (it.isNotEmpty()) {
                adBinding.adImage.setImageDrawable(it[0].drawable)
            }
        }

        // 매핑 설정 (NativeAdView가 올바르게 동작하기 위해 필요)
        val adView = adBinding.root as NativeAdView
        adView.headlineView = adBinding.adHeadline
        adView.bodyView = adBinding.adBody
        adView.callToActionView = adBinding.adCallToAction
        adView.iconView = adBinding.adIcon
        adView.advertiserView = adBinding.adAdvertiser
        adView.imageView = adBinding.adImage

        // 광고 등록
        adView.setNativeAd(nativeAd)

        // 기존 광고 컨테이너 비우고 새 광고 추가
        binding.adFrame.removeAllViews()
        binding.adFrame.addView(adView)
    }

    fun adDestroy() {
        nativeAd?.destroy()
    }
}