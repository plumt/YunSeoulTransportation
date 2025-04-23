package com.yun.yunseoultransportation.ui.components

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import com.yun.yunseoultransportation.BR
import com.yun.yunseoultransportation.R
import com.yun.yunseoultransportation.databinding.ViewSearchButtonBinding
import com.yun.yunseoultransportation.ui.dialog.RouteSearchDialog
import com.yun.yunseoultransportation.ui.dialog.RouteSearchInterface
import com.yun.yunseoultransportation.util.ViewUtil.setTextColorFromBackground
import com.yun.yunseoultransportation.util.extensions.setOnSingleClickListener

class TransportSearchButtonView<T : Any> @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private lateinit var binding: ViewSearchButtonBinding
    private lateinit var routeSearchDialog: RouteSearchDialog<T, ViewDataBinding>
    private var searchCallback: ((String) -> Unit)? = null
    private var selectedItemCallback: ((T) -> Unit)? = null
    private var searchType: Int = SEARCH_TYPE_BUS
    private var searchButtonText: String = ""
    private var searchButtonColor: Int = 0
    private var searchButtonImage: Int = 0

    companion object {
        const val SEARCH_TYPE_BUS = 0 // 버스 검색
        const val SEARCH_TYPE_SUBWAY = 1 // 지하철 검색
    }

    init {
        initView(attrs)
    }

    @Suppress("UNCHECKED_CAST")
    private fun initView(attrs: AttributeSet?) {
        binding = ViewSearchButtonBinding.inflate(LayoutInflater.from(context), this, true)

        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.TransportSearchButtonView
        ).run {
            searchButtonText =
                getString(R.styleable.TransportSearchButtonView_searchButtonText) ?: ""
            searchButtonImage =
                getResourceId(R.styleable.TransportSearchButtonView_searchButtonImage, 0)
            searchButtonColor = getColor(R.styleable.TransportSearchButtonView_searchButtonColor, 0)
            searchType = getInt(R.styleable.TransportSearchButtonView_searchType, SEARCH_TYPE_BUS)
            this
        }
        typedArray.recycle()

        if (searchButtonImage != 0) binding.ivIcon.setImageResource(searchButtonImage)
        binding.tvTitle.text = searchButtonText
        if (searchButtonColor != 0) {
            binding.cvContainer.setCardBackgroundColor(searchButtonColor)
            binding.tvTitle.setTextColorFromBackground(searchButtonColor)
        }

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
        binding.llContainer.setOnSingleClickListener {
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