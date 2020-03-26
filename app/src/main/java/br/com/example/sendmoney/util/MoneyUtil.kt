package br.com.example.sendmoney.util

import br.com.example.sendmoney.SendMoneyConsts
import java.text.NumberFormat

class MoneyUtil {

    companion object {

        fun format(value: Double): String {
            val locale = SendMoneyConsts.ENUS
            val numberFormat = NumberFormat.getCurrencyInstance(locale)
            return numberFormat.format(value)
        }
    }
}