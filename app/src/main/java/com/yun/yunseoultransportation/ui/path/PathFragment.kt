package com.yun.yunseoultransportation.ui.path

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.MapLifeCycleCallback
import com.yun.yunseoultransportation.BR
import com.yun.yunseoultransportation.R
import com.yun.yunseoultransportation.base.BaseFragment
import com.yun.yunseoultransportation.databinding.FragmentPathBinding
import com.yun.yunseoultransportation.ui.dialog.KeywordSearchDialog
import com.yun.yunseoultransportation.util.extensions.setOnSingleClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PathFragment : BaseFragment<FragmentPathBinding, PathViewModel>() {
    override val viewModel: PathViewModel by viewModels()
    override fun getResourceId(): Int = R.layout.fragment_path
    override fun isLoading(): LiveData<Boolean>? = null
    override fun isOnBackEvent(): Boolean = false
    override fun onBackEvent() {}
    override fun setVariable(): Int = BR.path

    private lateinit var keywordSearchDialog: KeywordSearchDialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val a =viewModel.searchInfoList.value!!
        keywordSearchDialog = KeywordSearchDialog(
            requireActivity()
        ) { keywordResult ->
            viewModel.keywordSearch(keywordResult)
        }

        binding.mapView.start(object : MapLifeCycleCallback() {
            override fun onMapDestroy() {}
            override fun onMapError(p0: Exception?) {}
        }, object : KakaoMapReadyCallback() {
            override fun onMapReady(kakaoMap: KakaoMap) {

            }
        })

        binding.cvInput.setOnSingleClickListener {
            keywordSearchDialog.show()
        }

        viewModel.searchInfoList.observe(viewLifecycleOwner) { searchInfoList ->
            keywordSearchDialog.keywordSearchDataUpdate(searchInfoList)
        }
    }
}