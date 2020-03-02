package br.com.example.sendmoney.util

class StringUtil {

    companion object {

        private val ELLIPSIS: String = "..."

        fun truncate(text: String, maxLength: Int): String {
            return if (text.length > maxLength) text.substring(0, maxLength) + ELLIPSIS else text
        }
    }

}