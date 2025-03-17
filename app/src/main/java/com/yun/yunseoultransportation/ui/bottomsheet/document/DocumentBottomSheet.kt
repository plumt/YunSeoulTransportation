package com.yun.yunseoultransportation.ui.bottomsheet.document

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.yun.yunseoultransportation.BR
import com.yun.yunseoultransportation.R
import com.yun.yunseoultransportation.databinding.DialogDocumentBottomSheetBinding
import com.yun.yunseoultransportation.data.model.search.keyworkSearch.Documents
import com.yun.yunseoultransportation.util.extensions.setOnSingleClickListener

interface DocumentBottomSheetInterface {
    fun onRoute(documents: com.yun.yunseoultransportation.data.model.search.keyworkSearch.Documents)
}

class DocumentBottomSheet(
    private val documentBottomSheetInterface: DocumentBottomSheetInterface
) : BottomSheetDialogFragment() {

    private lateinit var binding: DialogDocumentBottomSheetBinding
    private lateinit var documents: com.yun.yunseoultransportation.data.model.search.keyworkSearch.Documents

    override fun getTheme(): Int = R.style.RoundBottomSheetDialog
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        Log.d("yslee","onCreateDialog")
        val myDialog = super.onCreateDialog(savedInstanceState)
        myDialog.setOnShowListener { dialogInterface ->
            val bottomSheetDialog = dialogInterface as BottomSheetDialog

            // 드래그로 닫히는 기능 막기
            val bottomSheet =
                dialog?.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.let {
                val behavior = BottomSheetBehavior.from(it)
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
                behavior.skipCollapsed = true
                behavior.isDraggable = false // 드래그 기능 막기


//                it.setBackgroundResource(android.R.color.transparent)
            }
//            if (percent) setupRatio(bottomSheetDialog)
        }
        return myDialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("yslee","onCreateView")
        binding = DialogDocumentBottomSheetBinding.inflate(inflater, container, false)
        // dialog의 배경을 투명하게 설정
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
        )
        dialog?.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)

//        dialog?.setCanceledOnTouchOutside(false)

        val touchSideView = dialog!!.window?.decorView?.findViewById<View>(com.google.android.material.R.id.touch_outside)
        touchSideView?.setOnClickListener { null }

        binding.setVariable(BR.searchData, documents)

        binding.btnRoute.setOnSingleClickListener {
            if(this::documents.isInitialized){
                documentBottomSheetInterface.onRoute(documents)
                this.dismiss()
            }
        }

        return binding.root
    }

    fun onSelectedItem(documents: com.yun.yunseoultransportation.data.model.search.keyworkSearch.Documents){
        this.documents = documents
        if(this::binding.isInitialized){
            binding.setVariable(BR.searchData, documents)
        }
    }
}