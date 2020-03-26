package br.com.example.sendmoney.util

import br.com.example.sendmoney.SendMoneyConsts
import java.lang.RuntimeException
import java.text.NumberFormat
import java.util.*

class MoneyUtil {

    companion object {

        fun parse(value: String): Double {
            val locale = SendMoneyConsts.ENUS
            val numberFormat = NumberFormat.getCurrencyInstance(locale)
            val parse = numberFormat.parse(value)
            if (parse != null)
                return parse.toDouble()
            throw RuntimeException("Invalid parameter: $value")
        }

        fun format(value: Double): String {
            val locale = SendMoneyConsts.ENUS
            val numberFormat = NumberFormat.getCurrencyInstance(locale)
            return numberFormat.format(value)
        }

        fun getCurrencySymbol(): String {
            val locale: Locale = SendMoneyConsts.ENUS
            return NumberFormat.getCurrencyInstance(locale).currency?.getSymbol(locale).toString()
        }
    }
}