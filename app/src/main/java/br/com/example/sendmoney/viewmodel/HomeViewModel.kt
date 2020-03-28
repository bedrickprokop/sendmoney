package br.com.example.sendmoney.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.example.sendmoney.model.entity.User
import br.com.example.sendmoney.model.repository.TokenRepository

class HomeViewModel : ViewModel() {

    var currentUser: User? = null
    private val tokenRepository = TokenRepository.getInstance()

    fun loadTokenObservable(user: User): LiveData<String> {
        return tokenRepository.generate(user)
    }
}