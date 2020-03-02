package br.com.example.sendmoney.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import br.com.example.sendmoney.model.entity.Contact
import br.com.example.sendmoney.model.entity.User
import br.com.example.sendmoney.model.repository.TransferRepository
import br.com.example.sendmoney.model.repository.UserRepository

class ContactsViewModel(application: Application) : AndroidViewModel(application) {

    private val transferRepository = TransferRepository.getInstance()
    private val userRepository = UserRepository.getInstance()

    fun loadContactListObservable(user: User, token: String): LiveData<List<Contact>> {
        return userRepository.loadContacts(user, token)
    }

    fun sendMoney(contact: Contact, value: Double, token: String): LiveData<Boolean> {
        return transferRepository.sendMoney(contact, value, token)
    }
}