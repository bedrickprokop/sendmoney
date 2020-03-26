package br.com.example.sendmoney

import android.app.Application
import br.com.example.sendmoney.model.entity.User
import br.com.example.sendmoney.util.SharedUtil

class SendMoneyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        //Adicionado um usuário simulando que ele já tenha passado pela tela de login e que foi setado
        //no shared preferences
        SharedUtil.addUser(
            this, User(
                1, "Bedrick Prokop", "bedrick.prokop@gmail.com",
                null, null, null
            )
        )
    }
}