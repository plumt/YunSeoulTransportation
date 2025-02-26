package com.yun.yunseoultransportation.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import com.yun.yunseoultransportation.BR
import com.yun.yunseoultransportation.R
import com.yun.yunseoultransportation.base.BaseFragment
import com.yun.yunseoultransportation.base.setOnSingleClickListener
import com.yun.yunseoultransportation.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val viewModel: HomeViewModel by viewModels()
    override fun getResourceId(): Int = R.layout.fragment_home
    override fun isLoading(): LiveData<Boolean>? = null
    override fun isOnBackEvent(): Boolean = true
    override fun onBackEvent() { }
    override fun setVariable(): Int = BR.home


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSubway.setOnSingleClickListener {
            navigate(R.id.action_homeFragment_to_subwayFragment)
        }
        binding.btnBus.setOnSingleClickListener {
            navigate(R.id.action_homeFragment_to_busFragment)
        }
        binding.btnPath.setOnSingleClickListener {
            navigate(R.id.action_homeFragment_to_pathFragment)
        }
    }
}