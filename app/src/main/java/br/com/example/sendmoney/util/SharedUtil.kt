package br.com.example.sendmoney.util

import android.content.Context
import br.com.example.sendmoney.SendMoneyConsts
import br.com.example.sendmoney.model.entity.User

class SharedUtil {

    companion object {
        private val SEND_MONEY_PREFERENCES: String = "SEND_MONEY_PREFERENCES"
        private val DEFAULT_STR_VALUE: String = ""
        private val DEFAULT_INT_VALUE: Int = 0
        private val DEFAULT_BOOL_VALUE: Boolean = false


        fun addUser(context: Context, user: User) {
            user.id?.let { addInt(context, SendMoneyConsts.KEY_USER_ID, it) }
            user.name?.let { addString(context, SendMoneyConsts.KEY_USER_NAME, it) }
            user.email?.let { addString(context, SendMoneyConsts.KEY_USER_EMAIL, it) }
            user.token?.let { addString(context, SendMoneyConsts.KEY_USER_TOKEN, it) }
        }

        fun getUser(context: Context): User {
            return User(
                getInt(context, SendMoneyConsts.KEY_USER_ID),
                getString(context, SendMoneyConsts.KEY_USER_NAME),
                getString(context, SendMoneyConsts.KEY_USER_EMAIL),
                getString(context, SendMoneyConsts.KEY_USER_TOKEN),
                null,
                null
            )
        }

        fun addString(context: Context, key: String, value: String) {
            context.getSharedPreferences(SEND_MONEY_PREFERENCES, Context.MODE_PRIVATE).edit()
                .putString(key, value).apply()
        }

        fun addInt(context: Context, key: String, value: Int) {
            context.getSharedPreferences(SEND_MONEY_PREFERENCES, Context.MODE_PRIVATE).edit()
                .putInt(key, value).apply()
        }

        fun addBoolean(context: Context, key: String, value: Boolean) {
            context.getSharedPreferences(SEND_MONEY_PREFERENCES, Context.MODE_PRIVATE).edit()
                .putBoolean(key, value).apply()
        }

        fun getString(context: Context, key: String): String? {
            return context.getSharedPreferences(SEND_MONEY_PREFERENCES, Context.MODE_PRIVATE)
                .getString(key, DEFAULT_STR_VALUE)
        }

        fun getInt(context: Context, key: String): Int? {
            return context.getSharedPreferences(SEND_MONEY_PREFERENCES, Context.MODE_PRIVATE)
                .getInt(key, DEFAULT_INT_VALUE)
        }

        fun getBoolean(context: Context, key: String): Boolean? {
            return context.getSharedPreferences(SEND_MONEY_PREFERENCES, Context.MODE_PRIVATE)
                .getBoolean(key, DEFAULT_BOOL_VALUE)
        }
    }
}