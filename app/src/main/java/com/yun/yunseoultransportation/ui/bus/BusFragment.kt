package com.yun.yunseoultransportation.ui.bus

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.MapLifeCycleCallback
import com.yun.yunseoultransportation.R
import com.yun.yunseoultransportation.BR
import com.yun.yunseoultransportation.base.BaseFragment
import com.yun.yunseoultransportation.base.setOnSingleClickListener
import com.yun.yunseoultransportation.common.manager.map.KakaoMapManager
import com.yun.yunseoultransportation.databinding.FragmentBusBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BusFragment : BaseFragment<FragmentBusBinding, BusViewModel>() {
    override val viewModel: BusViewModel by viewModels()
    override fun getResourceId(): Int = R.layout.fragment_bus
    override fun isLoading(): LiveData<Boolean>? = null
    override fun isOnBackEvent(): Boolean = false
    override fun onBackEvent() {}
    override fun setVariable(): Int = BR.bus

    private lateinit var kakaoMapManager: KakaoMapManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGetBusPosByRtid.setOnSingleClickListener(listener = onSingleClickListener)
        binding.btnGetBusPosByVehId.setOnSingleClickListener(listener = onSingleClickListener)
        binding.btnGetBusRouteList.setOnSingleClickListener(listener = onSingleClickListener)


        binding.mapView.start(object : MapLifeCycleCallback() {
            override fun onMapDestroy() {}
            override fun onMapError(p0: Exception?) {}
        }, object : KakaoMapReadyCallback() {
            override fun onMapReady(kakaoMap: KakaoMap) {
                kakaoMapManager = KakaoMapManager(kakaoMap)
//                markerClickListener(kakaoMap)
            }
        })

        viewModel.busData.observe(viewLifecycleOwner) { busData ->
            Log.d("yslee","viewModel.busData.observe > $busData")
            if(busData.isNotEmpty()){
                kakaoMapManager.clearMarker()
                busData.forEach { item ->
                    kakaoMapManager.addMarker(item)
                }
                kakaoMapManager.bounces(busData)
            }
        }


    }

    private val onSingleClickListener: (View) -> Unit = {
        when (it.id) {
            binding.btnGetBusPosByVehId.id -> viewModel.getBusPosByVehId()
            binding.btnGetBusPosByRtid.id -> viewModel.getBusPosByRtid()
            binding.btnGetBusRouteList.id -> viewModel.getBusRouteList()
        }
    }
}