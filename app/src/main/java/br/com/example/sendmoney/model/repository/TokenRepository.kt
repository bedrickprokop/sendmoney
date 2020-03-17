package br.com.example.sendmoney.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.example.sendmoney.model.HttpApiGenerator
import br.com.example.sendmoney.model.api.TokenApi
import br.com.example.sendmoney.model.entity.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TokenRepository private constructor() {

    private var tokenApi: TokenApi = HttpApiGenerator<TokenApi>()
        .gen(TokenApi::class.java)

    companion object {
        private var instance: TokenRepository? = null

        fun getInstance(): TokenRepository {
            if (instance == null)
                instance = TokenRepository()
            return instance!!
        }
    }

    fun generate(user: User): LiveData<String> {
        val data = MutableLiveData<String>()
        val call = tokenApi.generate(user)
        call.enqueue(object : Callback<String> {

            override fun onResponse(call: Call<String>, response: Response<String>) {
                data.postValue(response.body())
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                //TODO fazer tratament de erro
            }
        })
        return data
    }

}