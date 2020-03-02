package br.com.example.sendmoney.model.api

import retrofit2.Call
import retrofit2.http.*

interface TransferApi {

    @GET("transfer")
    fun getTransfers(@Path("token") token: String)

    @FormUrlEncoded
    @POST("transfer/sendmoney")
    fun sendMoney(
        @Field("clientId") clientId: Int,
        @Field("value") value: Double,
        @Field("token") token: String
    ): Call<Boolean>
}