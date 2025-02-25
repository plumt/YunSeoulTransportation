package com.yun.yunseoultransportation.ui.bus

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import com.yun.yunseoultransportation.R
import com.yun.yunseoultransportation.BR
import com.yun.yunseoultransportation.base.BaseFragment
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.test()
    }
}