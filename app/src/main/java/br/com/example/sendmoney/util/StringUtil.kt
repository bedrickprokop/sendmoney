package br.com.example.sendmoney.util

class StringUtil {

    companion object {

        val ELLIPSIS: String = "..."
        val REAL_SIGN: String = "R$"
        val PERIOD: String = "."
        val COMMA: String = ","

        fun truncate(text: String, maxLength: Int): String {
            return if (text.length > maxLength) text.substring(0, maxLength) + ELLIPSIS else text
        }

        fun convertToMoneyForm(value: Double): String {
            return "$REAL_SIGN $value".replace(PERIOD, COMMA)
        }
    }
}