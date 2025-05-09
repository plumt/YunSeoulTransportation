package com.yun.yunseoultransportation.ui.bus

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.NaverMap.LAYER_GROUP_TRAFFIC
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.OverlayImage
import com.yun.yunseoultransportation.BR
import com.yun.yunseoultransportation.R
import com.yun.yunseoultransportation.base.BaseFragment
import com.yun.yunseoultransportation.common.manager.CountDownInterface
import com.yun.yunseoultransportation.common.manager.CountDownManager
import com.yun.yunseoultransportation.common.manager.map.NaverMapManager
import com.yun.yunseoultransportation.common.model.toBusMarker
import com.yun.yunseoultransportation.common.model.toBusStationMarker
import com.yun.yunseoultransportation.common.model.toNaverPolyline
import com.yun.yunseoultransportation.databinding.FragmentBusBinding
import com.yun.yunseoultransportation.domain.model.busStation.BusStationInfo
import com.yun.yunseoultransportation.ui.components.TransportSearchBarView
import com.yun.yunseoultransportation.util.Util.dpToPx
import com.yun.yunseoultransportation.util.Util.observeWithLifecycle
import com.yun.yunseoultransportation.util.Util.setStatusBarColor
import com.yun.yunseoultransportation.util.extensions.setOnSingleClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BusFragment : BaseFragment<FragmentBusBinding, BusViewModel>(), OnMapReadyCallback,
    CountDownInterface {
    override val viewModel: BusViewModel by viewModels()
    override fun getResourceId(): Int = R.layout.fragment_bus
    override fun isLoading(): LiveData<Boolean>? = null
    override fun isOnBackEvent(): Boolean = false
    override fun onBackEvent() {}
    override fun setVariable(): Int = BR.bus

    private lateinit var naverMap: NaverMap
    private lateinit var naverMapManager: NaverMapManager

    private lateinit var countDownManager: CountDownManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setStatusBarColor(
            requireActivity().window,
            ContextCompat.getColor(requireContext(), R.color.color_1B4E3B),
            false
        )

        val fm = childFragmentManager
        val naverMapView = fm.findFragmentById(binding.naverMapView.id) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(binding.naverMapView.id, it).commit()
            }

        naverMapView.getMapAsync(this)
        countDownManager = CountDownManager(this)


        binding.cvCountDown.setOnSingleClickListener(listener = onSingleClickListener)

        (binding.searchBar as TransportSearchBarView<BusStationInfo>).run {
            setOnSearchListener { keyword ->
//                viewModel.getBusRouteList(keyword)
//                viewModel.getLowStationByUid(keyword)
                viewModel.getStationByName(keyword)
            }
            setOnSelectedListener { item ->
//                viewModel.getRoutePath(item.busRouteId)
//                viewModel.getBusPosByRtid(item.busRouteId)
//                viewModel.getStaionByRoute(item.busRouteId)
                countDownManager.stopCountDown()
            }
        }

        viewModel.busRouteInfoList.observeWithLifecycle(viewLifecycleOwner) {
            binding.searchBar.updateData(it)
        }

        viewModel.busPathData.observeWithLifecycle(viewLifecycleOwner) {
            if (it.isNotEmpty() && this@BusFragment::naverMapManager.isInitialized) {
                naverMapManager.addPolyline(
                    it.toNaverPolyline(Color.BLUE, dpToPx(resources, 5).toInt())
                )
            }
        }

        viewModel.busStationList.observeWithLifecycle(viewLifecycleOwner) {
            if (it.isNotEmpty() && this@BusFragment::naverMapManager.isInitialized) {
                naverMapManager.addMarkers(
                    it.toBusStationMarker(),
                    "station",
                    isBounds = false,
                )
            }
        }

        viewModel.busData.observeWithLifecycle(viewLifecycleOwner) {
            if (it.isNotEmpty() && this@BusFragment::naverMapManager.isInitialized) {
                naverMapManager.addMarkers(
                    it.toBusMarker().map { item ->
                        item.apply {
                            overlayImage =
                                OverlayImage.fromResource(R.drawable.outline_directions_bus_24)
                            setScaleSize(resources, 40, 40)
                            tag = "bus"
                        }
                    },
                    "bus",
                    isBounds = false,
                    zIndex = 4,
                )
                countDownManager.startCountDown()
            }
        }
    }

    private val onSingleClickListener: (View) -> Unit = {
        when (it.id) {
            binding.cvCountDown.id -> {
                countDownManager.resetCountDown()
            }
        }
    }

    override fun onTick(seconds: Long) {
        binding.setVariable(BR.count_down, seconds.toString())
        if (seconds == 0L && viewModel.selectedBusRouteId.value.isNotEmpty()) {
            viewModel.getBusPosByRtid(viewModel.selectedBusRouteId.value)
        }
    }

    override fun onMapReady(naverMap: NaverMap) {
        this.naverMap = naverMap.apply {
            setLayerGroupEnabled(LAYER_GROUP_TRAFFIC, true)
        }
        naverMapManager = NaverMapManager(naverMap)

        arguments?.let {
            it.getString("busRouteId")?.let { busRouteId ->
                viewModel.getRoutePath(busRouteId)
                viewModel.getBusPosByRtid(busRouteId)
                viewModel.getStaionByRoute(busRouteId)
                countDownManager.stopCountDown()
            }
        }
    }

    override fun onDestroyView() {
        countDownManager.stopCountDown()
        super.onDestroyView()
    }
}