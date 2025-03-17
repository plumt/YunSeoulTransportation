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
import com.yun.yunseoultransportation.databinding.DialogRouteSearchBinding
import com.yun.yunseoultransportation.databinding.ItemRouteSearchBusInfoListBinding
import com.yun.yunseoultransportation.domain.model.busStation.BusStationInfo
import com.yun.yunseoultransportation.util.extensions.dialogResize
import com.yun.yunseoultransportation.util.extensions.setOnSingleClickListener

interface RouteSearchInterface {
    fun keywordResult(keyword: String)
    fun onSelectedItem(item: BusStationInfo)
}

class RouteSearchDialog(
    context: Context,
    private val routeSearchInterface: RouteSearchInterface
) : Dialog(context, R.style.FullScreenDialogStyle){

    private lateinit var binding: DialogRouteSearchBinding
    private var keyword: String = ""

    fun routeSearchDataUpdate(searchInfoList: List<BusStationInfo>) {
        if (this::binding.isInitialized) {
            binding.setVariable(BR.searchData, searchInfoList)
        }
    }

    override fun dismiss() {
        super.dismiss()
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
        binding.icInputEdit.etInput.setText("146")
        context.dialogResize(this@RouteSearchDialog, 1.0f, 1.0f)

        binding.icInputEdit.etInput.doOnTextChanged { text, start, before, count -> keyword = text.toString() }
        binding.icInputEdit.btnSearch.setOnSingleClickListener {
            if(keyword.isNotEmpty()){
                routeSearchInterface.keywordResult(keyword)
            } else {
                Toast.makeText(context, "검색어를 입력해 주세요", Toast.LENGTH_SHORT).show()
            }
        }

        binding.rvBusRouteSearch.run {
            adapter = object : BaseRecyclerViewAdapter.Create<BusStationInfo, ItemRouteSearchBusInfoListBinding>(
                layoutResId = R.layout.item_route_search_bus_info_list,
                bindingVariableId = BR.itemRouteSearchBusInfo,
                bindingListener = BR.routeSearchBusInfoListener
            ) {
                override fun onItemLongClick(item: BusStationInfo, view: View): Boolean = true
                override fun onItemClick(item: BusStationInfo, view: View) {
                    routeSearchInterface.onSelectedItem(item)
                }
            }
            setHasFixedSize(true)
            itemAnimator = null
        }
    }

}