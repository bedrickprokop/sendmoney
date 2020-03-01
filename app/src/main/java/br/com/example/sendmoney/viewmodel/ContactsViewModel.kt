package br.com.example.sendmoney.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.example.sendmoney.model.entity.Contact
import br.com.example.sendmoney.model.entity.User

class ContactsViewModel(application: Application) : AndroidViewModel(application) {

    fun loadContactListObservable(user: User): LiveData<List<Contact>> {
        val data = MutableLiveData<List<Contact>>()

        val mutableListOf = mutableListOf(
            Contact("Gollum", "(31) 90383-4001"),
            Contact("Frodo Baggins", "(31) 99801-2942"),
            Contact("Bilbo Baggins", "(11) 90383-4001"),
            Contact("Gandalf", "(12) 90383-4001"),
            Contact("Éowyn", "(21) 90383-4001"),
            Contact("Arwen Undómiel", "(11) 90383-4001"),
            Contact("Barbárvore", "(31) 90383-4001"),
            Contact("Velho Salgueiro-homem", "(98) 90383-4001"),
            Contact("Fredegar Bolger", "(97) 90383-4001"),
            Contact("Forlong, o Gordo", "(22) 90383-4001"),
            Contact("Radagast", "(11) 90383-4001"),
            Contact("Faramir", "(12) 90383-4001"),
            Contact("Rei Bruxo de Angmar", "(97) 90383-4001"),
            Contact("Thórin II Escudo de Carvalho", "(21) 90383-4001"),
            Contact("Grão Orc", "(11) 90383-4001")
        )
        data.value = mutableListOf
        return data
    }

}