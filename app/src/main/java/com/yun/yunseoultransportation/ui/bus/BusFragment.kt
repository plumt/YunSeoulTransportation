package com.yun.yunseoultransportation.ui.bus

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.NaverMap.LAYER_GROUP_TRAFFIC
import com.naver.maps.map.OnMapReadyCallback
import com.yun.yunseoultransportation.BR
import com.yun.yunseoultransportation.R
import com.yun.yunseoultransportation.base.BaseFragment
import com.yun.yunseoultransportation.common.manager.map.NaverMapManager
import com.yun.yunseoultransportation.databinding.FragmentBusBinding
import com.yun.yunseoultransportation.util.extensions.setOnSingleClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BusFragment : BaseFragment<FragmentBusBinding, BusViewModel>(), OnMapReadyCallback {
    override val viewModel: BusViewModel by viewModels()
    override fun getResourceId(): Int = R.layout.fragment_bus
    override fun isLoading(): LiveData<Boolean>? = null
    override fun isOnBackEvent(): Boolean = false
    override fun onBackEvent() {}
    override fun setVariable(): Int = BR.bus

    private lateinit var naverMap: NaverMap
    private lateinit var naverMapManager: NaverMapManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGetBusPosByRtid.setOnSingleClickListener(listener = onSingleClickListener)
        binding.btnGetBusPosByVehId.setOnSingleClickListener(listener = onSingleClickListener)
        binding.btnGetBusRouteList.setOnSingleClickListener(listener = onSingleClickListener)
        binding.btnGetRoutePath.setOnSingleClickListener(listener = onSingleClickListener)

        val fm = childFragmentManager
        val naverMapView = fm.findFragmentById(binding.naverMapView.id) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(binding.naverMapView.id, it).commit()
            }

        naverMapView.getMapAsync(this)

        viewModel.busPathData.observe(viewLifecycleOwner) { busPathData ->
            if(busPathData.isNotEmpty() && this::naverMapManager.isInitialized){
                naverMapManager.addPolyline(busPathData)
            }
        }

        viewModel.busData.observe(viewLifecycleOwner) { busData ->
            Log.d("yslee", "viewModel.busData.observe > $busData")
            if (busData.isNotEmpty() && this::naverMapManager.isInitialized) {
                naverMapManager.addMarkers(busData)
            }
        }


    }

    private val onSingleClickListener: (View) -> Unit = {
        when (it.id) {
            binding.btnGetBusPosByVehId.id -> viewModel.getBusPosByVehId()
            binding.btnGetBusPosByRtid.id -> viewModel.getBusPosByRtid()
            binding.btnGetBusRouteList.id -> viewModel.getBusRouteList()
            binding.btnGetRoutePath.id -> viewModel.getRoutePath()
        }
    }

    override fun onMapReady(naverMap: NaverMap) {
        Log.d("yslee", "onMapReady")
        this.naverMap = naverMap.apply {
            setLayerGroupEnabled(LAYER_GROUP_TRAFFIC, true)
        }
        naverMapManager = NaverMapManager(naverMap)
    }
}