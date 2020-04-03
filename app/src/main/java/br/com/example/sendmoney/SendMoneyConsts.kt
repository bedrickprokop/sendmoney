package br.com.example.sendmoney

import java.util.*


object SendMoneyConsts {
    /**
     * Languages
     */
    @JvmField
    val PTBR: Locale = Locale("pt", "BR")

    @JvmField
    val ENUS: Locale = Locale("en", "US")

    /**
     * URL's
     */
    const val BASE_URL: String = "http://localhost:8080/sendmoney/"

    /**
     * Intents
     */
    const val EXTRA_TOKEN: String = "TOKEN"

    /**
     * Request codes and message results
     */
    const val REQUEST_CODE_CONTACTS: Int = 100
    const val RESULT_MESSAGE: String = "MESSAGE"

    /**
     * SharedPreferences keys
     */
    const val KEY_USER_ID: String = "USER_ID"
    const val KEY_USER_NAME: String = "USER_NAME"
    const val KEY_USER_EMAIL: String = "USER_EMAIL"
    const val KEY_USER_TOKEN: String = "USER_TOKEN"

    /**
     * Text elements
     */
    const val PERIOD: String = "."
    const val COMMA: String = ","
    const val EMPTY: String = ""
    const val SINGLE_SPACE: String = " "
    const val ELLIPSIS: String = "..."

    /**
     * Number's
     */
}