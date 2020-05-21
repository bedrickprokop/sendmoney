package br.com.example.sendmoney.util

import br.com.example.sendmoney.config.SendMoneyConsts.ELLIPSIS


object StringUtil {

    fun truncate(text: String, maxLength: Int): String =
        if (text.length > maxLength) text.substring(0, maxLength) + ELLIPSIS else text
}