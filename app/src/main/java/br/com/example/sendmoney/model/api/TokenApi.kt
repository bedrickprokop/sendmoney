package br.com.example.sendmoney.model.api

import br.com.example.sendmoney.model.entity.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface TokenApi {

    @POST("token/generate")
    fun generate(@Body user: User?): Call<String>

}