package com.yun.yunseoultransportation.ui.path

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.MapLifeCycleCallback
import com.yun.yunseoultransportation.BR
import com.yun.yunseoultransportation.R
import com.yun.yunseoultransportation.base.BaseFragment
import com.yun.yunseoultransportation.databinding.FragmentPathBinding
import com.yun.yunseoultransportation.domain.model.search.keyworkSearch.Documents
import com.yun.yunseoultransportation.ui.bottomsheet.document.DocumentBottomSheet
import com.yun.yunseoultransportation.ui.bottomsheet.document.DocumentBottomSheetInterface
import com.yun.yunseoultransportation.ui.dialog.KeywordSearchDialog
import com.yun.yunseoultransportation.ui.dialog.KeywordSearchInterface
import com.yun.yunseoultransportation.util.extensions.setOnSingleClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PathFragment : BaseFragment<FragmentPathBinding, PathViewModel>(), KeywordSearchInterface, DocumentBottomSheetInterface {
    override val viewModel: PathViewModel by viewModels()
    override fun getResourceId(): Int = R.layout.fragment_path
    override fun isLoading(): LiveData<Boolean>? = null
    override fun isOnBackEvent(): Boolean = false
    override fun onBackEvent() {}
    override fun setVariable(): Int = BR.path

    private lateinit var keywordSearchDialog: KeywordSearchDialog
    private lateinit var documentBottomSheet: DocumentBottomSheet

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        keywordSearchDialog = KeywordSearchDialog(requireActivity(), this)
        documentBottomSheet = DocumentBottomSheet(this)

        binding.mapView.start(object : MapLifeCycleCallback() {
            override fun onMapDestroy() {}
            override fun onMapError(p0: Exception?) {}
        }, object : KakaoMapReadyCallback() {
            override fun onMapReady(kakaoMap: KakaoMap) {

            }
        })

//        binding.btnKeywordSearch.setOnSingleClickListener {
//            documentBottomSheet.show(requireActivity().supportFragmentManager, documentBottomSheet.tag)
//        }

        binding.cvInput.setOnSingleClickListener {
            keywordSearchDialog.show()
        }

        viewModel.searchInfoList.observe(viewLifecycleOwner) { searchInfoList ->
            keywordSearchDialog.keywordSearchDataUpdate(searchInfoList)
        }
    }

    // 키워드 검샥
    override fun keywordResult(keyword: String) {
        viewModel.keywordSearch(keyword)
    }

    // 리스트 아이템 선택
    override fun onSelectedItem(item: Documents) {
        Log.d("yslee","$${item}")
        keywordSearchDialog.dismiss()
        documentBottomSheet.onSelectedItem(item)
        documentBottomSheet.show(requireActivity().supportFragmentManager, documentBottomSheet.tag)
    }

    // dialog close
    override fun onDismiss() {
        viewModel.clearKeywordSearchDataList()
    }

    // 길찾기
    override fun onRoute(documents: Documents) {
        Log.d("yslee","길찾기 : $documents")
        viewModel.getPathInfoByBusNSub(documents.x, documents.y)
    }
}