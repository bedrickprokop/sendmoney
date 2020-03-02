package br.com.example.sendmoney.model.api

import br.com.example.sendmoney.model.entity.Contact
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {

    @GET("user/contacts/{token}")
    fun getContacts(@Path("token") token: String): Call<List<Contact>>
}