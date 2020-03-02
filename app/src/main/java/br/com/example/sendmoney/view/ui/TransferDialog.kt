package br.com.example.sendmoney.view.ui

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.Window
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import br.com.example.sendmoney.R
import br.com.example.sendmoney.databinding.DialogTransferBinding
import br.com.example.sendmoney.model.entity.Contact


class TransferDialog(
    private val mContext: Context,
    private val contact: Contact,
    private val listener: (Double) -> Unit
) :
    Dialog(mContext) {

    private lateinit var bind: DialogTransferBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        bind = DataBindingUtil.inflate(
            LayoutInflater.from(mContext),
            R.layout.dialog_transfer,
            null,
            false
        )
        setContentView(bind.root)
        setCancelable(false)

        val metrics: DisplayMetrics = mContext.resources.displayMetrics
        val width = metrics.widthPixels
        window?.setLayout((6 * width) / 7, ConstraintLayout.LayoutParams.WRAP_CONTENT)

        bind.tvContactName.text = contact.name
        bind.tvContactPhone.text = contact.phone
        bind.ibClose.setOnClickListener {
            dismiss()
        }
        bind.btTransferMoney.setOnClickListener {
            val strValue = bind.etTransferValue.text.toString()
            if (strValue.isNotEmpty() && strValue.isNotBlank()) {
                dismiss()
                listener(strValue.toDouble())
            }
        }
    }
}