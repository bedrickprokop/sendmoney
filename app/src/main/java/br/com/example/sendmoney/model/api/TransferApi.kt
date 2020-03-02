package br.com.example.sendmoney.model.api

import br.com.example.sendmoney.model.entity.Transfer
import retrofit2.Call
import retrofit2.http.*

interface TransferApi {

    @GET("transfer/{token}")
    fun getTransfers(@Path("token") token: String): Call<List<Transfer>>

    @FormUrlEncoded
    @POST("transfer/sendmoney")
    fun sendMoney(
        @Field("clientId") clientId: Int,
        @Field("value") value: Double,
        @Field("token") token: String
    ): Call<Boolean>
}