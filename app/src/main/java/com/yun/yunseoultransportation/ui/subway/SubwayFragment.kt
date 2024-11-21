package com.yun.yunseoultransportation.ui.subway

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import com.yun.yunseoultransportation.R
import com.yun.yunseoultransportation.BR
import com.yun.yunseoultransportation.base.BaseFragment
import com.yun.yunseoultransportation.databinding.FragmentSubwayBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SubwayFragment : BaseFragment<FragmentSubwayBinding, SubwayViewModel>() {
    override val viewModel: SubwayViewModel by viewModels()
    override fun getResourceId(): Int = R.layout.fragment_subway
    override fun isLoading(): LiveData<Boolean>? = null
    override fun isOnBackEvent(): Boolean = false
    override fun onBackEvent() {}
    override fun setVariable(): Int = BR.subway

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}