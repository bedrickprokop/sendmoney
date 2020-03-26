package br.com.example.sendmoney.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import br.com.example.sendmoney.SendMoneyConsts

class MaskUtil {

    companion object {

        fun genMoneyWatcher(etValue: EditText): TextWatcher {

            return object : TextWatcher {
                private val MAX_LENGTH: Int = 11
                private var current: String = SendMoneyConsts.EMPTY

                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                }

                override fun afterTextChanged(s: Editable) {
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    if (s.toString() != current) {
                        etValue.removeTextChangedListener(this)

                        val currencySymbol = MoneyUtil.getCurrencySymbol()
                        val regex = "[$currencySymbol${SendMoneyConsts.COMMA}${SendMoneyConsts.PERIOD}]".toRegex()

                        var strClean = s.toString().replace(regex, SendMoneyConsts.EMPTY)
                        if(strClean.length > MAX_LENGTH)
                            strClean = strClean.substring(0, MAX_LENGTH)

                        val parsed = strClean.toDouble()
                        val strFormatted = MoneyUtil.format(parsed / 100)

                        current = strFormatted
                        etValue.setText(current)
                        etValue.setSelection(current.length)
                        etValue.addTextChangedListener(this)
                    }
                }
            }
        }
    }
}