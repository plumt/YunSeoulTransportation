package com.yun.yunseoultransportation.ui.path

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.MapLifeCycleCallback
import com.kakao.vectormap.camera.CameraPosition
import com.kakao.vectormap.camera.CameraUpdateFactory
import com.yun.yunseoultransportation.BR
import com.yun.yunseoultransportation.R
import com.yun.yunseoultransportation.base.BaseFragment
import com.yun.yunseoultransportation.databinding.FragmentPathBinding
import com.yun.yunseoultransportation.ui.dialog.KeywordSearchDialog
import com.yun.yunseoultransportation.ui.dialog.KeywordSearchInterface
import com.yun.yunseoultransportation.util.extensions.setOnSingleClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PathFragment : BaseFragment<FragmentPathBinding, PathViewModel>(), KeywordSearchInterface {
    override val viewModel: PathViewModel by viewModels()
    override fun getResourceId(): Int = R.layout.fragment_path
    override fun isLoading(): LiveData<Boolean>? = null
    override fun isOnBackEvent(): Boolean = false
    override fun onBackEvent() {}
    override fun setVariable(): Int = BR.path

    private lateinit var keywordSearchDialog: KeywordSearchDialog

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    private lateinit var kakaoMap: KakaoMap

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        keywordSearchDialog = KeywordSearchDialog(requireActivity(), this)
        initialKakaoMap()
        initialBottomSheet()

        binding.cvInput.setOnSingleClickListener(listener = onSingleClickListener)
        binding.bottomSheet.icBottomSheetContents.btnRoute.setOnSingleClickListener(listener = onSingleClickListener)

        viewModel.searchInfoList.observe(viewLifecycleOwner) { searchInfoList ->
            keywordSearchDialog.keywordSearchDataUpdate(searchInfoList)
        }

    }

    private val onSingleClickListener: (View) -> Unit = {
        when (it.id) {
            binding.bottomSheet.icBottomSheetContents.btnRoute.id -> {
                val x = viewModel.selectedDocument.value?.x
                val y = viewModel.selectedDocument.value?.y
                if (x != null && y != null) {
//                    viewModel.getPathInfoByBusNSub(x, y)
                }
            }

            binding.cvInput.id -> keywordSearchDialog.show()
        }
    }

    // 카카오맴 셋팅
    private fun initialKakaoMap() {
        binding.mapView.start(object : MapLifeCycleCallback() {
            override fun onMapDestroy() {}
            override fun onMapError(p0: Exception?) {}
        }, object : KakaoMapReadyCallback() {
            override fun onMapReady(kakaoMap: KakaoMap) {
                this@PathFragment.kakaoMap = kakaoMap

            }
        })
    }

    // 바텀시트 셋팅
    private fun initialBottomSheet() {
        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheet.bottomSheet).apply {
            addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onSlide(bottomSheet: View, slideOffset: Float) {

                }

                override fun onStateChanged(bottomSheet: View, newState: Int) {
                }
            })

        }
    }

    // 키워드 검샥
    override fun keywordResult(keyword: String) {
//        viewModel.keywordSearch(keyword)
    }

    // 리스트 아이템 선택
    override fun onSelectedItem(item: com.yun.yunseoultransportation.data.model.search.keyworkSearch.Documents) {
        Log.d("yslee", "$${item}")
        keywordSearchDialog.dismiss()
//        viewModel.selectDocument(item)
        if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_HIDDEN) {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
        val lat = item.y.toDouble()
        val lng = item.x.toDouble()
        val cameraUpdate = CameraUpdateFactory.newCameraPosition(
            CameraPosition.from(lat, lng, 12, 0.0, 0.0, 0.0)
        )
        kakaoMap.moveCamera(cameraUpdate)

    }

    // dialog close
    override fun onDismiss() {
    }
}