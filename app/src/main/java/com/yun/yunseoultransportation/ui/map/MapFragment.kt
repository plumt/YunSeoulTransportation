package com.yun.yunseoultransportation.ui.map

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
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
import com.yun.yunseoultransportation.databinding.FragmentMapBinding
import com.yun.yunseoultransportation.domain.model.bus.busRouteList.ItemList
import com.yun.yunseoultransportation.ui.dialog.RouteSearchDialog
import com.yun.yunseoultransportation.ui.dialog.RouteSearchInterface
import com.yun.yunseoultransportation.util.extensions.setOnSingleClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapFragment : BaseFragment<FragmentMapBinding, MapViewModel>(), OnMapReadyCallback, RouteSearchInterface, CountDownInterface {
    override val viewModel: MapViewModel by viewModels()
    override fun getResourceId(): Int = R.layout.fragment_map
    override fun isLoading(): LiveData<Boolean>? = null
    override fun isOnBackEvent(): Boolean = true
    override fun onBackEvent() {}
    override fun setVariable(): Int = BR.map

    private lateinit var naverMap: NaverMap
    private lateinit var naverMapManager: NaverMapManager
    private lateinit var routeSearchDialog: RouteSearchDialog

    private lateinit var countDownManager : CountDownManager


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fm = childFragmentManager
        val naverMapView = fm.findFragmentById(binding.naverMapView.id) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(binding.naverMapView.id, it).commit()
            }

        naverMapView.getMapAsync(this)

        routeSearchDialog = RouteSearchDialog(requireActivity(), this)
        countDownManager = CountDownManager(this)

        binding.icInputBtn.layoutContainer.setOnSingleClickListener(listener = onSingleClickListener)
        binding.cvCountDown.setOnSingleClickListener(listener = onSingleClickListener)

        viewModel.busRouteInfoList.observe(viewLifecycleOwner){ busRouteInfoList ->
            routeSearchDialog.routeSearchDataUpdate(busRouteInfoList)
        }

        viewModel.busPathData.observe(viewLifecycleOwner) { busPathData ->
            if(busPathData.isNotEmpty() && this::naverMapManager.isInitialized){
                naverMapManager.addPolyline(busPathData)
            }
        }

        viewModel.busData.observe(viewLifecycleOwner) { busData ->
            if (busData.isNotEmpty() && this::naverMapManager.isInitialized) {
                naverMapManager.addMarkers(busData, resources = resources, size = 40, isBounds = false,
                    overlayImage = OverlayImage.fromResource(R.drawable.outline_directions_bus_24))
                countDownManager.startCountDown()
            }
        }
    }

    private val onSingleClickListener: (View) -> Unit = {
        when(it.id) {
            binding.icInputBtn.layoutContainer.id -> routeSearchDialog.show()
            binding.cvCountDown.id -> {
                countDownManager.resetCountDown()
            }
        }
    }

    // 카운트 다운
    override fun onTick(seconds: Long) {
        Log.d("yslee","onTick : $seconds")
        binding.setVariable(BR.count_down, seconds.toString())
        if(seconds == 0L && viewModel.selectedBusRouteId.value != null){
            viewModel.getBusPosByRtid(viewModel.selectedBusRouteId.value!!)
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

    override fun onSelectedItem(item: ItemList) {
        Log.d("yslee","onSelectedItem > $item")
        viewModel.getRoutePath(item.busRouteId)
        viewModel.getBusPosByRtid(item.busRouteId)
        routeSearchDialog.dismiss()
        countDownManager.stopCountDown()
    }

    override fun keywordResult(keyword: String) {
        viewModel.getBusRouteList(keyword)
        Log.d("yslee","keywordResult > $keyword")
    }


    override fun onDestroyView() {
        Log.d("yslee","onDestroy")
        countDownManager.stopCountDown()
        super.onDestroyView()
    }
}