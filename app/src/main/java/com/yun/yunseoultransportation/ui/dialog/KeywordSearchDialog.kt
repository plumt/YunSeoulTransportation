package com.yun.yunseoultransportation.ui.dialog

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.yun.yunseoultransportation.R
import com.yun.yunseoultransportation.databinding.DialogKeywordSearchBinding
import com.yun.yunseoultransportation.domain.model.search.keyworkSearch.KeywordSearchResponse

class KeywordSearchDialog(
    private val context: Context,
    private val onSearchResult: (KeywordSearchResponse) -> Unit
) {
    private lateinit var dialog: AlertDialog
    private lateinit var binding: DialogKeywordSearchBinding

    fun show(){
        binding = DialogKeywordSearchBinding.inflate(LayoutInflater.from(context))

        val builder = AlertDialog.Builder(context, R.style.FullScreenDialogStyle)
        builder.setView(binding.root)
        dialog = builder.create()

        dialog.window?.apply {
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            setGravity(Gravity.CENTER)
        }

        binding.tvTitle.text = "전체화면 텍스트2"

        dialog.show()
    }
}