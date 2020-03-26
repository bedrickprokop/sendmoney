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

        var phoneContentDesctiption =
            getString(R.string.dialog_transfer_tv_contact_phone_description)
        phoneContentDesctiption = String.format(phoneContentDesctiption, contact.phone)

        bind = DataBindingUtil.inflate(
            inflater, R.layout.dialog_transfer, container, false
        )
        bind.viewModel = ViewModelProvider(this).get(TransferViewModel::class.java)
        bind.tvContactName.text = contact.name
        bind.tvContactPhone.text = contact.phone
        bind.tvContactPhone.contentDescription = phoneContentDesctiption
        bind.ibClose.setOnClickListener {
            dismiss()
        }
        bind.btTransferMoney.setOnClickListener {
            val strValue = bind.etTransferValue.text.toString()
            if (bind.viewModel!!.isValidValue(strValue)) {
                dismiss()
                listener(strValue.toDouble())
            } else {
                //TODO notify user for validation error
            }
        }
        isCancelable = false

        return bind.root
    }
}