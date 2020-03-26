package br.com.example.sendmoney.util

import br.com.example.sendmoney.SendMoneyConsts

class StringUtil {

    companion object {

        fun truncate(text: String, maxLength: Int): String {
            return if (text.length > maxLength) text.substring(
                0,
                maxLength
            ) + SendMoneyConsts.ELLIPSIS else text
        }
    }
}