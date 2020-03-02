package br.com.example.sendmoney.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import br.com.example.sendmoney.model.entity.Contact
import br.com.example.sendmoney.model.entity.Transfer
import br.com.example.sendmoney.model.entity.User
import br.com.example.sendmoney.model.repository.TransferRepository

class TransferHistoryViewModel(application: Application) : AndroidViewModel(application) {

    private val transferRepository = TransferRepository.getInstance()

    fun loadTransferHistoryObservable(user: User, token: String): LiveData<List<Transfer>> {
        return transferRepository.loadTransferHistory(user, token)
    }
}