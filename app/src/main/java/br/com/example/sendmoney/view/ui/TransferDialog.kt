package br.com.example.sendmoney.view.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import br.com.example.sendmoney.R
import br.com.example.sendmoney.databinding.DialogTransferBinding
import br.com.example.sendmoney.model.entity.Contact
import br.com.example.sendmoney.viewmodel.TransferViewModel
import java.util.*

class TransferDialog(
    private val contact: Contact,
    private val listener: (Double) -> Unit
) :
    DialogFragment() {

    private lateinit var bind: DialogTransferBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Objects.requireNonNull<Window>(dialog?.window)
            .setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        bind = DataBindingUtil.inflate(
            inflater, R.layout.dialog_transfer, container, false
        )

        bind.viewModel = ViewModelProvider(this).get(TransferViewModel::class.java)

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
        isCancelable = false

        return bind.root
    }
}