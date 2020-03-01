package br.com.example.sendmoney.model

import br.com.example.sendmoney.SendMoneyConsts
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HttpApiGenerator<T> {

    fun gen(clazz: Class<T>): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(SendMoneyConsts.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(clazz)
    }
}