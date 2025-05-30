package com.yun.yunseoultransportation.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.ViewDataBinding
import com.yun.yunseoultransportation.BR
import com.yun.yunseoultransportation.R
import com.yun.yunseoultransportation.databinding.ViewSearchBarBinding
import com.yun.yunseoultransportation.ui.components.TransportSearchButtonView.Companion
import com.yun.yunseoultransportation.ui.dialog.RouteSearchDialog
import com.yun.yunseoultransportation.ui.dialog.RouteSearchInterface
import com.yun.yunseoultransportation.util.extensions.setOnSingleClickListener

class TransportSearchBarView<T : Any> @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private lateinit var binding: ViewSearchBarBinding
    private lateinit var routeSearchDialog: RouteSearchDialog<T, ViewDataBinding>
    private var searchCallback: ((String) -> Unit)? = null
    private var selectedItemCallback: ((T) -> Unit)? = null
    private var searchType: Int = SEARCH_TYPE_BUS

    companion object {
        const val SEARCH_TYPE_BUS = 0 // 버스 검색
        const val SEARCH_TYPE_SUBWAY = 1 // 지하철 검색
    }

    init {
        initView(attrs)
    }

    @Suppress("UNCHECKED_CAST")
    private fun initView(attrs: AttributeSet?){
        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.TransportSearchBarView
        ).run {
            searchType = getInt(R.styleable.TransportSearchButtonView_searchType,
                TransportSearchButtonView.SEARCH_TYPE_BUS
            )
            this
        }

        typedArray.recycle()
        binding = ViewSearchBarBinding.inflate(LayoutInflater.from(context), this, true)

        val (layoutResId, bindingVariableId, bindingListenerId) = when (searchType) {
            SEARCH_TYPE_BUS -> Triple(
                R.layout.item_transport_search_bus_info_list,
                BR.itemTransportSearchBusInfo,
                BR.transportSearchBusInfoListener
            )

            SEARCH_TYPE_SUBWAY -> Triple(
                R.layout.item_transport_search_bus_info_list,
                BR.itemTransportSearchBusInfo,
                BR.transportSearchBusInfoListener
            )

            else -> return
        }

        routeSearchDialog = RouteSearchDialog(
            context,
            object : RouteSearchInterface<T> {
                override fun keywordResult(keyword: String) {
                    searchCallback?.invoke(keyword)
                }

                override fun onSelectedItem(item: T) {
                    selectedItemCallback?.invoke(item)
                    routeSearchDialog.dismiss()
                }
            },
            layoutResId, bindingVariableId, bindingListenerId
        )

        binding.llSearch.setOnSingleClickListener {
            routeSearchDialog.show()
        }
    }

    fun setOnSearchListener(callback: (String) -> Unit) {
        this.searchCallback = callback
    }

    fun setOnSelectedListener(callback: (T) -> Unit) {
        this.selectedItemCallback = callback
    }

    fun updateData(searchInfoList: List<T>) {
        routeSearchDialog.routeSearchDataUpdate(searchInfoList)
    }
}