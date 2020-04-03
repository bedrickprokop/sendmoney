package br.com.example.sendmoney.view.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import br.com.example.sendmoney.R
import br.com.example.sendmoney.SendMoneyConsts.COMMA
import br.com.example.sendmoney.SendMoneyConsts.EMPTY
import br.com.example.sendmoney.SendMoneyConsts.PERIOD
import br.com.example.sendmoney.databinding.DialogTransferBinding
import br.com.example.sendmoney.model.entity.Contact
import br.com.example.sendmoney.util.MoneyUtil
import br.com.example.sendmoney.viewmodel.TransferViewModel
import java.util.*

class TransferDialog(
    private val contact: Contact,
    private val listener: (Double) -> Unit
) :
    DialogFragment() {

    private lateinit var bind: DialogTransferBinding
    private lateinit var mTextWatcher: TextWatcher

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Objects.requireNonNull<Window>(dialog?.window)
            .setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        var phoneDescription = getString(R.string.dialog_transfer_mtv_contact_phone_description)
        phoneDescription = String.format(phoneDescription, contact.phone)

        bind = DataBindingUtil.inflate(
            inflater, R.layout.dialog_transfer, container, false
        )
        bind.viewModel = ViewModelProvider(this).get(TransferViewModel::class.java)

        mTextWatcher = object : TextWatcher {
            private val MAX_LENGTH: Int = 11
            private var current: String = EMPTY

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun afterTextChanged(s: Editable) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.toString() != current) {
                    bind.tietTransferValue.removeTextChangedListener(this)

                    val currencySymbol = MoneyUtil.getCurrencySymbol()
                    val regex = "[$currencySymbol${COMMA}${PERIOD}]".toRegex()
                    var strClean = s.toString().replace(regex, EMPTY)
                    if (strClean.length > MAX_LENGTH)
                        strClean = strClean.substring(0, MAX_LENGTH)
                    val parsed = strClean.toDouble()
                    val enableTransferButton = parsed > 0.0
                    val strFormatted = MoneyUtil.format(parsed / 100)
                    current = strFormatted

                    bind.tietTransferValue.setText(current)
                    bind.tietTransferValue.setSelection(current.length)
                    bind.tietTransferValue.addTextChangedListener(this)
                    bind.mbTransferMoney.isEnabled = enableTransferButton
                }
            }
        }

        bind.ibClose.setOnClickListener {
            dismiss()
        }
        bind.mtvContactName.text = contact.name
        bind.mtvContactPhone.text = contact.phone
        bind.mtvContactPhone.contentDescription = phoneDescription
        bind.tietTransferValue.addTextChangedListener(mTextWatcher)
        bind.mbTransferMoney.isEnabled = false
        bind.mbTransferMoney.setOnClickListener {
            val doubleValue = MoneyUtil.parse(bind.tietTransferValue.text.toString())
            dismiss()
            listener(doubleValue)
        }
        isCancelable = false
        return bind.root
    }
}
