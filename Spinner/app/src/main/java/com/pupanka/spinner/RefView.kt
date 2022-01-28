package com.pupanka.spinner

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.view.ViewGroup
import android.view.Window
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.view_ref.*

@SuppressLint("DefaultLocale")
class RefView(
    context: Context,
    textTitle: String,
    items: ArrayList<RefModel>,
    var callback: (RefModel) -> Unit
) :
    Dialog(context), RefSpinnerAdapter.ClickListener {
    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window?.setBackgroundDrawableResource(R.drawable.bg_dialog_rounded_white)
        setContentView(R.layout.view_ref)
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        setCancelable(true)
        setCanceledOnTouchOutside(true)
        popupTitle.text = textTitle
        val adapter = RefSpinnerAdapter(context,items,this)
        listRefData.layoutManager = LinearLayoutManager(context)
        listRefData.adapter = adapter
    }

    fun open(): RefView {
        show()
        return this
    }

    override fun onClick(data: RefModel) {
        callback(data)
        dismiss()
    }
}