package com.yun.yunseoultransportation.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.yun.yunseoultransportation.BR
import com.yun.yunseoultransportation.R
import com.yun.yunseoultransportation.base.BaseFullScreenDialog
import com.yun.yunseoultransportation.base.BaseRecyclerViewAdapter
import com.yun.yunseoultransportation.databinding.DialogRouteSearchBinding
import com.yun.yunseoultransportation.databinding.ItemRouteSearchBusInfoListBinding
import com.yun.yunseoultransportation.domain.model.busStation.BusStationInfo
import com.yun.yunseoultransportation.util.extensions.dialogResize
import com.yun.yunseoultransportation.util.extensions.setOnSingleClickListener

interface RouteSearchInterface<T> {
    fun keywordResult(keyword: String)
    fun onSelectedItem(item: T)
}

class RouteSearchDialog<T : Any, B : ViewDataBinding>(
    context: Context,
    private val routeSearchInterface: RouteSearchInterface<T>,
    private val layoutResId: Int,
    private val bindingVariableId: Int,
    private val bindingListenerId: Int,
) : BaseFullScreenDialog(context) {

    private lateinit var binding: DialogRouteSearchBinding
    private var keyword: String = ""

    fun routeSearchDataUpdate(searchInfoList: List<T>) {
        if (this::binding.isInitialized) {
            binding.setVariable(BR.searchData, searchInfoList)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.dialog_route_search,
            null,
            false
        )
        setContentView(binding.root)


        keyword = "146"
        binding.icInputEdit.etInput.setText(keyword)


        context.dialogResize(this, 1.0f, 1.0f)

        binding.icInputEdit.etInput.doOnTextChanged { text, _, _, _ ->
            keyword = text.toString()
        }

        binding.icInputEdit.btnSearch.setOnSingleClickListener {
            if (keyword.isNotEmpty()) {
                routeSearchInterface.keywordResult(keyword)
            } else {
                Toast.makeText(context, "검색어를 입력해 주세요", Toast.LENGTH_SHORT).show()
            }
        }

        binding.rvBusRouteSearch.run {
            adapter = object : BaseRecyclerViewAdapter.Create<T, B>(
                layoutResId = layoutResId,
                bindingVariableId = bindingVariableId,
                bindingListener = bindingListenerId
            ) {
                override fun onItemLongClick(item: T, view: View): Boolean = true
                override fun onItemClick(item: T, view: View) {
                    routeSearchInterface.onSelectedItem(item)
                }
            }
            setHasFixedSize(true)
            itemAnimator = null
        }
    }
}