package com.yun.yunseoultransportation.ui.path

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import com.yun.yunseoultransportation.R
import com.yun.yunseoultransportation.BR
import com.yun.yunseoultransportation.base.BaseFragment
import com.yun.yunseoultransportation.base.setOnSingleClickListener
import com.yun.yunseoultransportation.databinding.FragmentPathBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PathFragment: BaseFragment<FragmentPathBinding, PathViewModel>(){
    override val viewModel: PathViewModel by viewModels()
    override fun getResourceId(): Int = R.layout.fragment_path
    override fun isLoading(): LiveData<Boolean>? = null
    override fun isOnBackEvent(): Boolean = false
    override fun onBackEvent() { }
    override fun setVariable(): Int = BR.path

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnPathInfoByBusNSubR.setOnSingleClickListener {
            viewModel.getPathInfoByBusNSub()
        }
    }
}