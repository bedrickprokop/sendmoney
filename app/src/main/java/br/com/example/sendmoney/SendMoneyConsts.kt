package br.com.example.sendmoney

import java.util.*

interface SendMoneyConsts {

    //TODO organizar constantes
    companion object {

        //Config
        val BASE_URL: String = "http://localhost:8080/sendmoney/"

        //Intent
        val EXTRA_TOKEN: String = "TOKEN"

        //Request codes and message results
        val REQUEST_CODE_CONTACTS: Int = 100
        val RESULT_MESSAGE: String = "MESSAGE"

        //SharedPreferences keys
        val KEY_USER_ID: String = "USER_ID"
        val KEY_USER_NAME: String = "USER_NAME"
        val KEY_USER_EMAIL: String = "USER_EMAIL"
        val KEY_USER_TOKEN: String = "USER_TOKEN"

        //Money
        val PTBR: Locale = Locale("pt", "BR")
        val ENUS: Locale = Locale("en", "US")

        //String
        val PERIOD: String = "."
        val COMMA: String = ","
        val EMPTY: String = ""
        val SINGLE_SPACE: String = " "
        val ELLIPSIS: String = "..."
    }
}