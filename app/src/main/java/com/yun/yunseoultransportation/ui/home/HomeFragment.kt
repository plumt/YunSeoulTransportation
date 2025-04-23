package com.yun.yunseoultransportation.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import com.yun.yunseoultransportation.BR
import com.yun.yunseoultransportation.R
import com.yun.yunseoultransportation.base.BaseFragment
import com.yun.yunseoultransportation.databinding.FragmentHomeBinding
import com.yun.yunseoultransportation.domain.model.busStation.BusStationInfo
import com.yun.yunseoultransportation.ui.components.TransportSearchButtonView
import com.yun.yunseoultransportation.util.Util.setStatusBarColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val viewModel: HomeViewModel by viewModels()
    override fun getResourceId(): Int = R.layout.fragment_home
    override fun isLoading(): LiveData<Boolean>? = null
    override fun isOnBackEvent(): Boolean = true
    override fun onBackEvent() {}
    override fun setVariable(): Int = BR.home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setStatusBarColor(requireActivity().window, Color.WHITE)
        viewModel.getNowWeather("서울시 화곡동")

        (binding.btnSearchBus as TransportSearchButtonView<BusStationInfo>).run {
            setOnSearchListener { keyword ->
//                viewModel.getBusRouteList(keyword)
            }
            setOnSelectedListener { item ->
//                viewModel.getRoutePath(item.busRouteId)
//                viewModel.getBusPosByRtid(item.busRouteId)
//                viewModel.getStaionByRoute(item.busRouteId)
//                countDownManager.stopCountDown()
            }
        }

    }

    override fun onDestroyView() {
        binding.nativeAd.adDestroy()
        super.onDestroyView()
    }
}