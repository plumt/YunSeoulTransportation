package com.yun.yunseoultransportation.ui.components

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.yun.yunseoultransportation.databinding.ViewSearchBarBinding
import com.yun.yunseoultransportation.domain.model.busStation.BusStationInfo
import com.yun.yunseoultransportation.ui.dialog.RouteSearchDialog
import com.yun.yunseoultransportation.ui.dialog.RouteSearchInterface
import com.yun.yunseoultransportation.util.extensions.setOnSingleClickListener

class BusSearchBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr), RouteSearchInterface {

    private lateinit var binding: ViewSearchBarBinding
    private lateinit var routeSearchDialog: RouteSearchDialog
    private var searchCallback: ((String) -> Unit)? = null
    private var selectedItemCallback: ((BusStationInfo) -> Unit)? = null

    init {
        initView()
    }

    private fun initView() {
        binding = ViewSearchBarBinding.inflate(LayoutInflater.from(context), this, true)
        routeSearchDialog = RouteSearchDialog(context, this)
        binding.llSearch.setOnSingleClickListener {
            routeSearchDialog.show()
        }
    }

    override fun onSelectedItem(item: BusStationInfo) {
        selectedItemCallback?.invoke(item)
        routeSearchDialog.dismiss()
    }

    override fun keywordResult(keyword: String) {
        searchCallback?.invoke(keyword)
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