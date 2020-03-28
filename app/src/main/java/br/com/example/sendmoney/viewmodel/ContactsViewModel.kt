package br.com.example.sendmoney.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.example.sendmoney.model.entity.Contact
import br.com.example.sendmoney.model.entity.User
import br.com.example.sendmoney.model.repository.TransferRepository
import br.com.example.sendmoney.model.repository.UserRepository

class ContactsViewModel : ViewModel() {

    private val transferRepository = TransferRepository.getInstance()
    private val userRepository = UserRepository.getInstance()

    fun loadContactListObservable(user: User): LiveData<List<Contact>> {
        return userRepository.loadContacts(user)
    }

    fun sendMoney(contact: Contact?, value: Double?, token: String?): LiveData<Boolean> {
        return transferRepository.sendMoney(contact, value, token)
    }
}
