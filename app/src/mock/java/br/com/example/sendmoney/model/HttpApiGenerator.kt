package br.com.example.sendmoney.model

import br.com.example.sendmoney.SendMoneyConsts.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HttpApiGenerator<T> {

    fun gen(clazz: Class<T>): T {

        val builder = OkHttpClient.Builder()
        builder.addInterceptor(MockClient())

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(builder.build())
            .build()
        return retrofit.create(clazz)
    }
}