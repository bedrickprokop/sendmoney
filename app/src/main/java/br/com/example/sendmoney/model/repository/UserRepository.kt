package br.com.example.sendmoney.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.example.sendmoney.model.HttpApiGenerator
import br.com.example.sendmoney.model.api.UserApi
import br.com.example.sendmoney.model.entity.Contact
import br.com.example.sendmoney.model.entity.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {

    private var userApi: UserApi = HttpApiGenerator<UserApi>()
        .gen(UserApi::class.java)

    companion object {
        private var instance: UserRepository? = null

        fun getInstance(): UserRepository {
            if (instance == null)
                instance = UserRepository()
            return instance!!
        }
    }

    fun loadContacts(user: User): LiveData<List<Contact>> {
        val data = MutableLiveData<List<Contact>>()
        val call = userApi.getContacts(user.token)

        call.enqueue(object : Callback<List<Contact>> {

            override fun onResponse(call: Call<List<Contact>>, response: Response<List<Contact>>) {
                data.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Contact>>, t: Throwable) {
                //TODO fazer tratament de erro
                data.postValue(arrayListOf())
            }
        })
        return data
    }
}