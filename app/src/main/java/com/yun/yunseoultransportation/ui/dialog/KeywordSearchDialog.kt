package com.yun.yunseoultransportation.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import com.yun.yunseoultransportation.BR
import com.yun.yunseoultransportation.R
import com.yun.yunseoultransportation.base.BaseRecyclerViewAdapter
import com.yun.yunseoultransportation.databinding.DialogKeywordSearchBinding
import com.yun.yunseoultransportation.databinding.ItemKeywordSearchInfoListBinding
import com.yun.yunseoultransportation.data.model.search.keyworkSearch.Documents
import com.yun.yunseoultransportation.util.extensions.dialogResize
import com.yun.yunseoultransportation.util.extensions.setOnSingleClickListener

interface KeywordSearchInterface {
    fun keywordResult(keyword: String)
    fun onSelectedItem(item: com.yun.yunseoultransportation.data.model.search.keyworkSearch.Documents)
    fun onDismiss()
}

class KeywordSearchDialog(
    private val context: Context,
    private val keywordSearchInterface: KeywordSearchInterface
) : Dialog(context, R.style.FullScreenDialogStyle) {
    private lateinit var binding: DialogKeywordSearchBinding
    private var keyword: String = ""

    fun keywordSearchDataUpdate(searchInfoList: List<com.yun.yunseoultransportation.data.model.search.keyworkSearch.Documents>) {
        if (this::binding.isInitialized) {
            binding.setVariable(BR.searchData, searchInfoList)
        }
    }

    override fun dismiss() {
        super.dismiss()
        keywordSearchInterface.onDismiss()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.dialog_keyword_search,
            null,
            false
        )
        setContentView(binding.root)

        context.dialogResize(this@KeywordSearchDialog, 1.0f, 1.0f)

        keyword = "메가박스"

        binding.etInput.doOnTextChanged { text, start, before, count -> keyword = text.toString() }
        binding.btnKeywordSearch.setOnSingleClickListener {
            if (keyword.isNotEmpty()) {
                keywordSearchInterface.keywordResult(keyword)
            } else {
                Toast.makeText(context, "검색어를 입력해 주세요", Toast.LENGTH_SHORT).show()
            }
        }

        binding.rvKeywordSearch.run {
            adapter = object :
                BaseRecyclerViewAdapter.Create<com.yun.yunseoultransportation.data.model.search.keyworkSearch.Documents, ItemKeywordSearchInfoListBinding>(
                    layoutResId = R.layout.item_keyword_search_info_list,
                    bindingVariableId = BR.itemKeyWordSearchInfo,
                    bindingListener = BR.keywordSearchInfoListener
                ) {
                override fun onItemLongClick(item: com.yun.yunseoultransportation.data.model.search.keyworkSearch.Documents, view: View): Boolean = true
                override fun onItemClick(item: com.yun.yunseoultransportation.data.model.search.keyworkSearch.Documents, view: View) {
                    keywordSearchInterface.onSelectedItem(item)

                    //Documents(
                    // place_name=메가박스 강남,
                    // distance=,
                    // place_url=http://place.map.kakao.com/10609442,
                    // category_name=문화,예술 > 영화,영상 > 영화관 > 메가박스,
                    // address_name=서울 서초구 서초동 1317-20,
                    // road_address_name=서울 서초구 서초대로77길 3,
                    // id=10609442, phone=1544-0070,
                    // category_group_code=CT1,
                    // category_group_name=문화시설,
                    // x=127.026417015264,
                    // y=37.4979560555237)
                }
            }
//            addItemDecoration(RecyclerViewDecorationHorizontal(7f.fromDpToPx()))
            setHasFixedSize(true)
            itemAnimator = null
        }

    }
}
