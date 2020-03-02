package br.com.example.sendmoney.model.repository

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

    fun sendMoney(contact: Contact, value: Double, token: String): LiveData<Boolean> {
        val data = MutableLiveData<Boolean>()
        val call = transferApi.sendMoney(contact.id, value, token)

        call.enqueue(object : Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                data.postValue(response.body())
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                //TODO fazer tratament de erro
                data.postValue(false)
            }
        })
        return data
    }

    fun loadTransferHistory(user: User, token: String): LiveData<List<Transfer>> {
        val data = MutableLiveData<List<Transfer>>()
        val call = transferApi.getTransfers(token)

        call.enqueue(object : Callback<List<Transfer>> {
            override fun onResponse(
                call: Call<List<Transfer>>,
                response: Response<List<Transfer>>
            ) {
                data.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Transfer>>, t: Throwable) {
                //TODO fazer tratament de erro
                data.postValue(arrayListOf())
            }
        })
        return data
    }
}