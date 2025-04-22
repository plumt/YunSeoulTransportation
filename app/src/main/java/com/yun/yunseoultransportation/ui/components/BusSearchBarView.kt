package com.yun.yunseoultransportation.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.yun.yunseoultransportation.R
import com.yun.yunseoultransportation.BR
import com.yun.yunseoultransportation.databinding.ItemRouteSearchBusInfoListBinding
import com.yun.yunseoultransportation.databinding.ViewBusSearchBarBinding
import com.yun.yunseoultransportation.domain.model.busStation.BusStationInfo
import com.yun.yunseoultransportation.ui.dialog.RouteSearchDialog
import com.yun.yunseoultransportation.ui.dialog.RouteSearchInterface
import com.yun.yunseoultransportation.util.extensions.setOnSingleClickListener

class BusSearchBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private lateinit var binding: ViewBusSearchBarBinding
    private lateinit var routeSearchDialog: RouteSearchDialog<BusStationInfo, ItemRouteSearchBusInfoListBinding>
    private var searchCallback: ((String) -> Unit)? = null
    private var selectedItemCallback: ((BusStationInfo) -> Unit)? = null

    init {
        initView()
    }

    private fun initView() {
        binding = ViewBusSearchBarBinding.inflate(LayoutInflater.from(context), this, true)
        routeSearchDialog = RouteSearchDialog(context,
            object : RouteSearchInterface<BusStationInfo> {
                override fun keywordResult(keyword: String) {
                    searchCallback?.invoke(keyword)
                }

                override fun onSelectedItem(item: BusStationInfo) {
                    selectedItemCallback?.invoke(item)
                    routeSearchDialog.dismiss()
                }
            },
            R.layout.item_route_search_bus_info_list,
            BR.itemRouteSearchBusInfo,
            BR.routeSearchBusInfoListener,)
        binding.llSearch.setOnSingleClickListener {
            routeSearchDialog.show()
        }
    }

    fun setOnSearchListener(callback: (String) -> Unit) {
        this.searchCallback = callback
    }

    fun setOnSelectedListener(callback: (BusStationInfo) -> Unit) {
        this.selectedItemCallback = callback
    }

    fun updateData(searchInfoList: List<BusStationInfo>) {
        routeSearchDialog.routeSearchDataUpdate(searchInfoList)
    }
}