package br.com.example.sendmoney.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.example.sendmoney.model.entity.Transfer
import br.com.example.sendmoney.model.entity.User
import br.com.example.sendmoney.model.repository.TransferRepository

class TransferHistoryViewModel : ViewModel() {

    private val transferRepository = TransferRepository.getInstance()

    fun loadTransferHistoryObservable(user: User): LiveData<List<Transfer>> {
        return transferRepository.loadTransferHistory(user)
    }
}