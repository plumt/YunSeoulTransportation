package com.yun.yunseoultransportation.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import com.yun.yunseoultransportation.BR
import com.yun.yunseoultransportation.R
import com.yun.yunseoultransportation.base.BaseRecyclerViewAdapter
import com.yun.yunseoultransportation.databinding.DialogKeywordSearchBinding
import com.yun.yunseoultransportation.databinding.ItemKeywordSearchInfoListBinding
import com.yun.yunseoultransportation.domain.model.search.keyworkSearch.Documents
import com.yun.yunseoultransportation.util.extensions.dialogResize
import com.yun.yunseoultransportation.util.extensions.setOnSingleClickListener

class KeywordSearchDialog(
    private val context: Context,
//    private val onSearchResult: (KeywordSearchResponse) -> Unit
    private val keywordResult: (String) -> Unit
) : Dialog(context, R.style.FullScreenDialogStyle) {
    private lateinit var binding: DialogKeywordSearchBinding
    private var keyword: String = ""

    fun keywordSearchDataUpdate(searchInfoList: List<Documents>) {
        if (this::binding.isInitialized) {
            binding.setVariable(BR.searchData, searchInfoList)
        }
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
        binding.btnKeywordSearch.setOnSingleClickListener { keywordResult(keyword) }

        binding.rvKeywordSearch.run {
            adapter = object :
                BaseRecyclerViewAdapter.Create<Documents, ItemKeywordSearchInfoListBinding>(
                    layoutResId = R.layout.item_keyword_search_info_list,
                    bindingVariableId = BR.itemKeyWordSearchInfo,
                    bindingListener = BR.keywordSearchInfoListener
                ) {
                override fun onItemLongClick(item: Documents, view: View): Boolean = true
                override fun onItemClick(item: Documents, view: View) {
                    Log.d("yslee", "onItemClick : $item")
                }
            }
//            addItemDecoration(RecyclerViewDecorationHorizontal(7f.fromDpToPx()))
            setHasFixedSize(true)
            itemAnimator = null
        }

    }
}
