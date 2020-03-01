package br.com.example.sendmoney.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import br.com.example.sendmoney.model.entity.User
import br.com.example.sendmoney.model.repository.TokenRepository

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    var currentUser: User? = null
    private val tokenRepository = TokenRepository.getInstance(/*application*/)

    fun loadTokenObservable(user: User): LiveData<String> {
        return tokenRepository.generate(user)
    }
}