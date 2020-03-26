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
import br.com.example.sendmoney.util.MaskUtil
import br.com.example.sendmoney.util.MoneyUtil
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

        var phoneDescription = getString(R.string.dialog_transfer_tv_contact_phone_description)
        phoneDescription = String.format(phoneDescription, contact.phone)

        bind = DataBindingUtil.inflate(
            inflater, R.layout.dialog_transfer, container, false
        )
        bind.viewModel = ViewModelProvider(this).get(TransferViewModel::class.java)
        bind.ibClose.setOnClickListener {
            dismiss()
        }
        bind.tvContactName.text = contact.name
        bind.tvContactPhone.text = contact.phone
        bind.tvContactPhone.contentDescription = phoneDescription
        bind.etTransferValue.hint = MoneyUtil.format(0.0)
        bind.etTransferValue.addTextChangedListener(MaskUtil.genMoneyWatcher(bind.etTransferValue))
        bind.btTransferMoney.setOnClickListener {
            val doubleValue = MoneyUtil.parse(bind.etTransferValue.text.toString())
            if (bind.viewModel!!.isValidValue(doubleValue)) {
                dismiss()
                listener(doubleValue)
            } else {
                //TODO habilitar o botão somente se os valores digitados forem válidos
                //TODO notify user for validation error
            }
        }
        isCancelable = false

        return bind.root
    }
}