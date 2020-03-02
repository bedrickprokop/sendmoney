package br.com.example.sendmoney.model.repository

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.example.sendmoney.model.HttpApiGenerator
import br.com.example.sendmoney.model.api.TransferApi
import br.com.example.sendmoney.model.entity.Contact
import br.com.example.sendmoney.model.entity.Transfer
import br.com.example.sendmoney.model.entity.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransferRepository {

    private var transferApi: TransferApi =
        HttpApiGenerator<TransferApi>().gen(TransferApi::class.java)

    companion object {
        private var instance: TransferRepository? = null

        fun getInstance(): TransferRepository {
            if (instance == null)
                instance = TransferRepository()
            return instance!!
        }
    }

    fun loadContacts(user: User, token: String): LiveData<List<Contact>> {
        val data = MutableLiveData<List<Contact>>()
        val mutableListOf = mutableListOf(
            Contact(1, "Gollum", "(31) 90383-4001"),
            Contact(2, "Frodo Baggins", "(31) 99801-2942"),
            Contact(3, "Bilbo Baggins", "(11) 90383-4001"),
            Contact(4, "Gandalf", "(12) 90383-4001"),
            Contact(5, "Éowyn", "(21) 90383-4001"),
            Contact(6, "Arwen Undómiel", "(11) 90383-4001"),
            Contact(7, "Barbárvore", "(31) 90383-4001"),
            Contact(8, "Velho Salgueiro-homem", "(98) 90383-4001"),
            Contact(9, "Fredegar Bolger", "(97) 90383-4001"),
            Contact(10, "Forlong, o Gordo", "(22) 90383-4001"),
            Contact(11, "Radagast", "(11) 90383-4001"),
            Contact(12, "Faramir", "(12) 90383-4001"),
            Contact(13, "Rei Bruxo de Angmar", "(97) 90383-4001"),
            Contact(14, "Thórin II Escudo de Carvalho", "(21) 90383-4001"),
            Contact(15, "Grão Orc", "(11) 90383-4001")
        )
        data.value = mutableListOf
        return data
    }

    fun sendMoney(contact: Contact, value: Double, token: String): LiveData<Boolean> {
        val data = MutableLiveData<Boolean>()
        val call = transferApi.sendMoney(contact.id, value, token)

        call.enqueue(object : Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                data.postValue(response.body())
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                Handler().postDelayed({
                    data.postValue(false)
                }, 3000)
            }
        })
        return data
    }

    fun loadTransferHistory(user: User, token: String): LiveData<List<Transfer>> {
        val data = MutableLiveData<List<Transfer>>()
        /*val call = transferApi.getTransfers(token)
        call.enqueue(object : Callback<List<Transfer>> {

            override fun onResponse(
                call: Call<List<Transfer>>, response: Response<List<Transfer>>
            ) {
                data.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Transfer>>, t: Throwable) {
                //TODO mock
            }
        })*/

        val mutableListOf = mutableListOf(
            Transfer(1, "Gollum", "(31) 90383-4001", 1200.30),
            Transfer(2, "Frodo Baggins", "(31) 99801-2942", 90.2),
            Transfer(3, "Bilbo Baggins", "(11) 90383-4001", 20.10),
            Transfer(4, "Gandalf", "(12) 90383-4001", 800.00),
            Transfer(5, "Éowyn", "(21) 90383-4001", 599.02),
            Transfer(6, "Arwen Undómiel", "(11) 90383-4001", 120.23),
            Transfer(7, "Barbárvore", "(31) 90383-4001", 0.00),
            Transfer(8, "Velho Salgueiro-homem", "(98) 90383-4001", 0.00),
            Transfer(9, "Fredegar Bolger", "(97) 90383-4001", 5490.04),
            Transfer(10, "Forlong, o Gordo", "(22) 90383-4001", 1200.30),
            Transfer(11, "Radagast", "(11) 90383-4001", 200.0),
            Transfer(12, "Faramir", "(12) 90383-4001", 727.2),
            Transfer(13, "Rei Bruxo de Angmar", "(97) 90383-4001", 11.1),
            Transfer(14, "Thórin II Escudo de Carvalho", "(21) 90383-4001", 2.1),
            Transfer(15, "Grão Orc", "(11) 90383-4001", 0.01)
        )
        data.value = mutableListOf
        return data
    }
}