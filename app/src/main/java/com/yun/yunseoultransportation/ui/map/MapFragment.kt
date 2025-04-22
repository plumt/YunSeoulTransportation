package com.yun.yunseoultransportation.ui.map

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
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
import com.yun.yunseoultransportation.databinding.FragmentMapBinding
import com.yun.yunseoultransportation.databinding.ItemRouteSearchBusInfoListBinding
import com.yun.yunseoultransportation.domain.model.busStation.BusStationInfo
import com.yun.yunseoultransportation.ui.dialog.RouteSearchDialog
import com.yun.yunseoultransportation.ui.dialog.RouteSearchInterface
import com.yun.yunseoultransportation.util.Util.dpToPx
import com.yun.yunseoultransportation.util.Util.observeWithLifecycle
import com.yun.yunseoultransportation.util.extensions.setOnSingleClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MapFragment : BaseFragment<FragmentMapBinding, MapViewModel>(), OnMapReadyCallback, CountDownInterface {
    override val viewModel: MapViewModel by viewModels()
    override fun getResourceId(): Int = R.layout.fragment_map
    override fun isLoading(): LiveData<Boolean>? = null
    override fun isOnBackEvent(): Boolean = true
    override fun onBackEvent() {}
    override fun setVariable(): Int = BR.map

    private lateinit var naverMap: NaverMap
    private lateinit var naverMapManager: NaverMapManager
    private lateinit var routeSearchDialog: RouteSearchDialog<BusStationInfo, ItemRouteSearchBusInfoListBinding>

    private lateinit var countDownManager: CountDownManager


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fm = childFragmentManager
        val naverMapView = fm.findFragmentById(binding.naverMapView.id) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(binding.naverMapView.id, it).commit()
            }

        naverMapView.getMapAsync(this)

        routeSearchDialog = RouteSearchDialog(requireActivity(),
            object : RouteSearchInterface<BusStationInfo> {
                override fun keywordResult(keyword: String) {
                    viewModel.getBusRouteList(keyword)
                }

                override fun onSelectedItem(item: BusStationInfo) {
                    // 버스 정보 처리
                    viewModel.getRoutePath(item.busRouteId)
                    viewModel.getBusPosByRtid(item.busRouteId)
                    viewModel.getStaionByRoute(item.busRouteId)
                    routeSearchDialog.dismiss()
                    countDownManager.stopCountDown()
                }
            },
            R.layout.item_route_search_bus_info_list,
            BR.itemRouteSearchBusInfo,
            BR.routeSearchBusInfoListener,
            )
        countDownManager = CountDownManager(this)

        binding.icInputBtn.llSearch.setOnSingleClickListener(listener = onSingleClickListener)
        binding.cvCountDown.setOnSingleClickListener(listener = onSingleClickListener)


//        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.RESUMED){
//                viewModel.busRouteInfoList.collect {
//
//                }
//            }
//        }

        lifecycleScope.launch {
            viewModel.busRouteInfoList.collect {
                routeSearchDialog.routeSearchDataUpdate(it)
            }
        }

//        viewModel.busRouteInfoList.observeWithLifecycle(viewLifecycleOwner) {
//            viewModel.busRouteInfoList.collect {
//                routeSearchDialog.routeSearchDataUpdate(it)
//            }
//        }

        viewModel.busPathData.observeWithLifecycle(viewLifecycleOwner) {
            if (it.isNotEmpty() && this@MapFragment::naverMapManager.isInitialized) {
                naverMapManager.addPolyline(
                    it.toNaverPolyline(Color.BLUE, dpToPx(resources, 5).toInt())
                )
            }
        }

        viewModel.busStationList.observeWithLifecycle(viewLifecycleOwner) {
            if (it.isNotEmpty() && this@MapFragment::naverMapManager.isInitialized) {
                naverMapManager.addMarkers(
                    it.toBusStationMarker(),
                    "station",
                    isBounds = false,
                )
            }
        }

        viewModel.busData.observeWithLifecycle(viewLifecycleOwner) {
            if (it.isNotEmpty() && this@MapFragment::naverMapManager.isInitialized) {
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
            binding.icInputBtn.llSearch.id -> routeSearchDialog.show()
            binding.cvCountDown.id -> {
                countDownManager.resetCountDown()
            }
        }
    }

    // 카운트 다운
    override fun onTick(seconds: Long) {
        Log.d("yslee", "onTick : $seconds")
        binding.setVariable(BR.count_down, seconds.toString())
        if (seconds == 0L && viewModel.selectedBusRouteId.value.isNotEmpty()) {
            viewModel.getBusPosByRtid(viewModel.selectedBusRouteId.value)
        }
    }

    // 지도 로딩 완료
    override fun onMapReady(naverMap: NaverMap) {
        this.naverMap = naverMap
        this.naverMap = naverMap.apply {
            setLayerGroupEnabled(LAYER_GROUP_TRAFFIC, true)
        }
        naverMapManager = NaverMapManager(naverMap)
    }

    override fun onDestroyView() {
        Log.d("yslee", "onDestroy")
        countDownManager.stopCountDown()
        super.onDestroyView()
    }
}